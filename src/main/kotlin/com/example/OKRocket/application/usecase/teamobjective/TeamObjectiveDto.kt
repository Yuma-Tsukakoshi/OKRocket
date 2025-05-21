package com.example.OKRocket.application.usecase.teamobjective

import com.example.OKRocket.domain.objective.KeyResult
import com.example.OKRocket.domain.objective.Objective
import java.time.LocalDate

data class CreateTeamObjectiveInput(
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val teamId: String,
    val creatorId: String
)

data class CreateTeamObjectiveOutput(
    val objective: Objective,
    val keyResults: List<KeyResult>
)

data class AddTeamKeyResultInput(
    val objectiveId: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val assignee: String
)

data class UpdateTeamObjectiveStatusInput(
    val objectiveId: String,
    val status: ObjectiveStatus,
    val operatorId: String
)

data class CreateTeamReflectionInput(
    val objectiveId: String,
    val title: String,
    val description: String,
    val operatorId: String
)

data class CreateTeamGakutikaInput(
    val reflectionId: String,
    val title: String,
    val description: String,
    val operatorId: String
)
