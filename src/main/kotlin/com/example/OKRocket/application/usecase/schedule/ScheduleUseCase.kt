package com.example.OKRocket.application.usecase.schedule

import com.example.OKRocket.domain.objective.ObjectiveRepository
import com.example.OKRocket.domain.objective.ObjectiveType
import java.time.LocalDate

class ScheduleUseCase(
    private val objectiveRepository: ObjectiveRepository,
    private val keyResultRepository: KeyResultRepository,
    private val teamRepository: TeamRepository
) {
    fun getSchedule(input: GetScheduleInput): List<ScheduleItem> {
        val scheduleItems = mutableListOf<ScheduleItem>()

        // 個人目標の取得
        val personalObjectives = objectiveRepository.findByUserId(input.userId)
            .filter { it.type == ObjectiveType.PERSONAL }
            .filter { isDateInRange(it.startDate, it.endDate, input.startDate, input.endDate) }
            .map { objective ->
                ScheduleItem(
                    id = objective.id,
                    title = objective.title,
                    description = objective.description,
                    startDate = objective.startDate,
                    endDate = objective.endDate,
                    type = ScheduleItemType.PERSONAL_OBJECTIVE
                )
            }
        scheduleItems.addAll(personalObjectives)

        // チーム目標の取得
        val teamMemberships = teamRepository.findMembershipsByTeamId(input.userId)
        val teamObjectives = teamMemberships.flatMap { membership ->
            objectiveRepository.findByTeamId(membership.teamId)
                .filter { it.type == ObjectiveType.TEAM }
                .filter { isDateInRange(it.startDate, it.endDate, input.startDate, input.endDate) }
                .map { objective ->
                    ScheduleItem(
                        id = objective.id,
                        title = objective.title,
                        description = objective.description,
                        startDate = objective.startDate,
                        endDate = objective.endDate,
                        type = ScheduleItemType.TEAM_OBJECTIVE
                    )
                }
        }
        scheduleItems.addAll(teamObjectives)

        // 個人の成果指標の取得
        val personalKeyResults = personalObjectives.flatMap { objective ->
            keyResultRepository.findByObjectiveId(objective.id)
                .filter { isDateInRange(it.startDate, it.endDate, input.startDate, input.endDate) }
                .map { keyResult ->
                    ScheduleItem(
                        id = keyResult.id,
                        title = keyResult.description,
                        description = keyResult.description,
                        startDate = keyResult.startDate,
                        endDate = keyResult.endDate,
                        type = ScheduleItemType.PERSONAL_KEY_RESULT
                    )
                }
        }
        scheduleItems.addAll(personalKeyResults)

        // チームの成果指標の取得（アサインされているもの）
        val teamKeyResults = teamObjectives.flatMap { objective ->
            keyResultRepository.findByObjectiveId(objective.id)
                .filter { it.assignee == input.userId }
                .filter { isDateInRange(it.startDate, it.endDate, input.startDate, input.endDate) }
                .map { keyResult ->
                    ScheduleItem(
                        id = keyResult.id,
                        title = keyResult.description,
                        description = keyResult.description,
                        startDate = keyResult.startDate,
                        endDate = keyResult.endDate,
                        type = ScheduleItemType.TEAM_KEY_RESULT
                    )
                }
        }
        scheduleItems.addAll(teamKeyResults)

        return scheduleItems.sortedBy { it.startDate }
    }

    private fun isDateInRange(
        startDate: LocalDate,
        endDate: LocalDate,
        rangeStart: LocalDate,
        rangeEnd: LocalDate
    ): Boolean {
        return !startDate.isAfter(rangeEnd) && !endDate.isBefore(rangeStart)
    }
}
