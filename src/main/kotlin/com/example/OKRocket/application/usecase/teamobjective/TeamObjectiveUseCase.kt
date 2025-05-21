package com.example.OKRocket.application.usecase.teamobjective

import com.example.OKRocket.domain.objective.KeyResult
import com.example.OKRocket.domain.objective.Objective
import com.example.OKRocket.domain.objective.ObjectiveRepository
import com.example.OKRocket.domain.objective.ObjectiveType
import com.example.OKRocket.domain.objective.Reflection
import com.example.OKRocket.domain.objective.ReflectionRepository
import com.example.OKRocket.domain.team.TeamRepository
import java.util.UUID

class TeamObjectiveUseCase(
    private val objectiveRepository: ObjectiveRepository,
    private val keyResultRepository: KeyResultRepository,
    private val reflectionRepository: ReflectionRepository,
    private val gakutikaRepository: GakutikaRepository,
    private val teamRepository: TeamRepository
) {
    fun createObjective(input: CreateTeamObjectiveInput): CreateTeamObjectiveOutput {
        // チームの存在確認と権限チェック
        val team = teamRepository.findById(input.teamId)
            ?: throw IllegalStateException("Team not found")

        val membership = teamRepository.findMembershipsByTeamId(team.id)
            .find { it.userId == input.creatorId }
            ?: throw IllegalStateException("Creator is not a member of the team")

        if (!membership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Creator does not have permission to create team objective")
        }

        val objective = Objective(
            id = generateId(),
            title = input.title,
            description = input.description,
            startDate = input.startDate,
            endDate = input.endDate,
            status = ObjectiveStatus.NOT_STARTED,
            type = ObjectiveType.TEAM
        )
        val savedObjective = objectiveRepository.save(objective)

        return CreateTeamObjectiveOutput(
            objective = savedObjective,
            keyResults = emptyList()
        )
    }

    fun addKeyResult(input: AddTeamKeyResultInput): KeyResult {
        val objective = objectiveRepository.findById(input.objectiveId)
            ?: throw IllegalStateException("Objective not found")

        if (objective.type != ObjectiveType.TEAM) {
            throw IllegalStateException("Objective is not a team objective")
        }

        val keyResult = KeyResult(
            id = generateId(),
            description = input.description,
            startDate = input.startDate,
            endDate = input.endDate,
            status = ObjectiveStatus.NOT_STARTED,
            assignee = input.assignee,
            objectiveId = objective.id
        )
        return keyResultRepository.save(keyResult)
    }

    fun updateObjectiveStatus(input: UpdateTeamObjectiveStatusInput) {
        val objective = objectiveRepository.findById(input.objectiveId)
            ?: throw IllegalStateException("Objective not found")

        if (objective.type != ObjectiveType.TEAM) {
            throw IllegalStateException("Objective is not a team objective")
        }

        val team = teamRepository.findById(objective.id)
            ?: throw IllegalStateException("Team not found")

        val membership = teamRepository.findMembershipsByTeamId(team.id)
            .find { it.userId == input.operatorId }
            ?: throw IllegalStateException("Operator is not a member of the team")

        if (!membership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Operator does not have permission to update team objective")
        }

        val updatedObjective = objective.copy(status = input.status)
        objectiveRepository.save(updatedObjective)
    }

    fun createReflection(input: CreateTeamReflectionInput): Reflection {
        val objective = objectiveRepository.findById(input.objectiveId)
            ?: throw IllegalStateException("Objective not found")

        if (objective.type != ObjectiveType.TEAM) {
            throw IllegalStateException("Objective is not a team objective")
        }

        if (objective.status != ObjectiveStatus.COMPLETED) {
            throw IllegalStateException("Cannot create reflection for non-completed objective")
        }

        val team = teamRepository.findById(objective.id)
            ?: throw IllegalStateException("Team not found")

        val membership = teamRepository.findMembershipsByTeamId(team.id)
            .find { it.userId == input.operatorId }
            ?: throw IllegalStateException("Operator is not a member of the team")

        if (!membership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Operator does not have permission to create reflection")
        }

        val reflection = Reflection(
            id = generateId(),
            title = input.title,
            description = input.description,
            objectiveId = objective.id
        )
        return reflectionRepository.save(reflection)
    }

    fun createGakutika(input: CreateTeamGakutikaInput): Gakutika {
        val reflection = reflectionRepository.findById(input.reflectionId)
            ?: throw IllegalStateException("Reflection not found")

        val objective = objectiveRepository.findById(reflection.objectiveId)
            ?: throw IllegalStateException("Objective not found")

        if (objective.type != ObjectiveType.TEAM) {
            throw IllegalStateException("Objective is not a team objective")
        }

        val team = teamRepository.findById(objective.id)
            ?: throw IllegalStateException("Team not found")

        val membership = teamRepository.findMembershipsByTeamId(team.id)
            .find { it.userId == input.operatorId }
            ?: throw IllegalStateException("Operator is not a member of the team")

        if (!membership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Operator does not have permission to create gakutika")
        }

        val gakutika = Gakutika(
            id = generateId(),
            title = input.title,
            description = input.description,
            reflectionId = reflection.id,
            isTeamOrigin = true
        )
        return gakutikaRepository.save(gakutika)
    }

    private fun generateId(): String = java.util.UUID.randomUUID().toString()
}
