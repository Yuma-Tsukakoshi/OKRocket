package com.example.OKRocket.presentation.mapper

import com.example.OKRocket.domain.objective.Objective
import com.example.OKRocket.presentation.dto.CreatePersonalObjectiveRequest
import com.example.OKRocket.presentation.dto.CreatePersonalObjectiveResponse
import com.example.OKRocket.usecase.personalobjective.CreatePersonalObjectiveInput
import com.example.OKRocket.usecase.personalobjective.CreatePersonalObjectiveOutput
import org.springframework.stereotype.Component

@Component
class PersonalObjectiveMapper {
    fun toCreateObjectiveInput(request: CreatePersonalObjectiveRequest): CreatePersonalObjectiveInput {
        return CreatePersonalObjectiveInput(
            title = request.title,
            description = request.description,
            startDate = request.startDate,
            endDate = request.endDate,
            userId = request.userId
        )
    }

    fun toCreateObjectiveResponse(output: CreatePersonalObjectiveOutput): CreatePersonalObjectiveResponse {
        return CreatePersonalObjectiveResponse(
            id = output.objective.id,
            title = output.objective.title,
            description = output.objective.description,
            startDate = output.objective.startDate,
            endDate = output.objective.endDate,
            status = output.objective.status.name,
            type = output.objective.type.name,
            keyResults = output.keyResults.map { kr ->
                CreatePersonalObjectiveResponse.KeyResult(
                    id = kr.id,
                    description = kr.description,
                    startDate = kr.startDate,
                    endDate = kr.endDate,
                    status = kr.status.name,
                    assignee = kr.assignee
                )
            }
        )
    }

    fun toCreateObjectiveResponse(objective: Objective): CreatePersonalObjectiveResponse {
        return CreatePersonalObjectiveResponse(
            id = objective.id,
            title = objective.title,
            description = objective.description,
            startDate = objective.startDate,
            endDate = objective.endDate,
            status = objective.status.name,
            type = objective.type.name,
            keyResults = emptyList()
        )
    }
}
