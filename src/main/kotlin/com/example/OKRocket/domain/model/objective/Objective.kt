package com.example.OKRocket.domain.model.objective

import com.example.OKRocket.domain.model.keyresult.KeyResult
import com.example.OKRocket.domain.model.status.Status
import java.time.LocalDate

class Objective(
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
