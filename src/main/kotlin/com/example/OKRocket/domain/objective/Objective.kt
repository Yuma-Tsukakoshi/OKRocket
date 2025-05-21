package com.example.OKRocket.domain.objective

import java.time.LocalDate

data class Objective(
    val id: String,
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: Status,
    val type: ObjectiveType,
    val keyResults: List<KeyResult>
)

enum class Status {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED
}

enum class ObjectiveType {
    PERSONAL,
    TEAM
}
