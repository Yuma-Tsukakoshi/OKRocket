package com.example.OKRocket.domain.objective

import java.time.LocalDate

data class KeyResult(
    val id: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: Status,
    val objectiveId: String,
    val assignee: String?
)
