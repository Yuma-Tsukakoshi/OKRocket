package com.example.OKRocket.application.usecase.notify

import com.example.OKRocket.domain.objective.ObjectiveRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class NotifyUseCase(
    private val objectiveRepository: ObjectiveRepository,
    private val keyResultRepository: KeyResultRepository
) {
    fun getNotificationTargets(date: LocalDate): List<NotificationTarget> {
        val targets = mutableListOf<NotificationTarget>()

        // 目標が作成されたときの通知
        val newObjectives = objectiveRepository.findAll()
            .filter { it.createdAt.toLocalDate() == date }
            .map { objective ->
                NotificationTarget(
                    id = objective.id,
                    type = NotificationType.OBJECTIVE_CREATED,
                    title = objective.title,
                    description = objective.description,
                    startDate = objective.startDate,
                    endDate = objective.endDate,
                    objectiveType = objective.type
                )
            }
        targets.addAll(newObjectives)

        // 成果指標の開始日とステータスが未着手の通知
        val startedKeyResults = keyResultRepository.findAll()
            .filter { it.startDate == date && it.status == ObjectiveStatus.NOT_STARTED }
            .map { keyResult ->
                val objective = objectiveRepository.findById(keyResult.objectiveId)
                    ?: throw IllegalStateException("Objective not found")
                NotificationTarget(
                    id = keyResult.id,
                    type = NotificationType.KEY_RESULT_STARTED,
                    title = keyResult.description,
                    description = keyResult.description,
                    startDate = keyResult.startDate,
                    endDate = keyResult.endDate,
                    objectiveType = objective.type
                )
            }
        targets.addAll(startedKeyResults)

        // 成果指標の期限が近い通知（1週間前、3日前、1日前）
        val dueKeyResults = keyResultRepository.findAll()
            .filter { keyResult ->
                val daysUntilDue = ChronoUnit.DAYS.between(date, keyResult.endDate)
                (daysUntilDue == 7L || daysUntilDue == 3L || daysUntilDue == 1L) &&
                    keyResult.status != ObjectiveStatus.COMPLETED
            }
            .map { keyResult ->
                val objective = objectiveRepository.findById(keyResult.objectiveId)
                    ?: throw IllegalStateException("Objective not found")
                NotificationTarget(
                    id = keyResult.id,
                    type = NotificationType.KEY_RESULT_DUE_SOON,
                    title = keyResult.description,
                    description = keyResult.description,
                    startDate = keyResult.startDate,
                    endDate = keyResult.endDate,
                    objectiveType = objective.type
                )
            }
        targets.addAll(dueKeyResults)

        return targets
    }

    fun registerNotificationChannel(input: RegisterNotificationChannelInput) {
        // TODO: 通知チャンネルの登録処理を実装
        // Slack APIを使用してチャンネルを登録
    }

    fun sendNotification(target: NotificationTarget, channel: NotificationChannel) {
        // TODO: 通知送信処理を実装
        // Slack APIを使用して通知を送信
    }
}
