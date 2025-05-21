package com.example.OKRocket.presentation.dto

import java.time.LocalDate

data class CreatePersonalObjectiveRequest(
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val userId: String
)

data class CreatePersonalObjectiveResponse(
    val id: String,
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: String,
    val type: String,
    val keyResults: List<KeyResult>
) {
    data class KeyResult(
        val id: String,
        val description: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val status: String,
        val assignee: String?
    )
}
