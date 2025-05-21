package com.example.OKRocket.application.usecase.personalobjective

import com.example.OKRocket.domain.objective.KeyResult
import com.example.OKRocket.domain.objective.Objective
import java.time.LocalDate

data class CreatePersonalObjectiveInput(
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val userId: String
)

data class CreatePersonalObjectiveOutput(
    val objective: Objective,
    val keyResults: List<KeyResult>
)

data class AddKeyResultInput(
    val objectiveId: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)

data class UpdateObjectiveStatusInput(
    val objectiveId: String,
    val status: ObjectiveStatus
)

data class CreateReflectionInput(
    val objectiveId: String,
    val title: String,
    val description: String
)

data class CreateGakutikaInput(
    val reflectionId: String,
    val title: String,
    val description: String
)
