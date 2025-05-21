package com.example.OKRocket.usecase.schedule

import java.time.LocalDate

data class GetScheduleInput(
    val userId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)

data class ScheduleItem(
    val id: String,
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val type: ScheduleItemType
)

enum class ScheduleItemType {
    PERSONAL_OBJECTIVE,
    TEAM_OBJECTIVE,
    PERSONAL_KEY_RESULT,
    TEAM_KEY_RESULT
}
