package com.example.OKRocket.usecase.notify

import com.example.OKRocket.domain.objective.ObjectiveType
import java.time.LocalDate

data class NotificationTarget(
    val id: String,
    val type: NotificationType,
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val objectiveType: ObjectiveType
)

enum class NotificationType {
    OBJECTIVE_CREATED,
    KEY_RESULT_STARTED,
    KEY_RESULT_DUE_SOON
}

data class NotificationChannel(
    val id: String,
    val name: String,
    val type: ChannelType
)

enum class ChannelType {
    SLACK
}

data class RegisterNotificationChannelInput(
    val userId: String,
    val channelId: String,
    val channelType: ChannelType
)
