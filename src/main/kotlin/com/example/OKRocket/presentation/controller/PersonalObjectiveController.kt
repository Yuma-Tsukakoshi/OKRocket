package com.example.OKRocket.presentation.controller

import com.example.OKRocket.application.usecase.personalobjective.PersonalObjectiveUseCase
import com.example.OKRocket.presentation.dto.CreatePersonalObjectiveRequest
import com.example.OKRocket.presentation.dto.CreatePersonalObjectiveResponse
import com.example.OKRocket.presentation.mapper.PersonalObjectiveMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/personal-objectives")
class PersonalObjectiveController(
    private val personalObjectiveUseCase: PersonalObjectiveUseCase,
    private val personalObjectiveMapper: PersonalObjectiveMapper
) {
    @PostMapping
    fun createObjective(@RequestBody request: CreatePersonalObjectiveRequest): CreatePersonalObjectiveResponse {
        val input = personalObjectiveMapper.toCreateObjectiveInput(request)
        val output = personalObjectiveUseCase.createObjective(input)
        return personalObjectiveMapper.toCreateObjectiveResponse(output)
    }

    @GetMapping("/{id}")
    fun getObjective(@PathVariable id: String): CreatePersonalObjectiveResponse {
        val objective = personalObjectiveUseCase.getObjective(id)
        return personalObjectiveMapper.toCreateObjectiveResponse(objective)
    }

    @PostMapping("/{objectiveId}/key-results")
    fun addKeyResult(
        @PathVariable objectiveId: String,
        @RequestBody request: Map<String, Any>
    ): ResponseEntity<Map<String, Any>> {
        val input = PersonalObjectiveMapper.toAddKeyResultInput(
            objectiveId = objectiveId,
            description = request["description"] as? String ?: throw IllegalArgumentException("description is required"),
            startDate = LocalDate.parse(request["startDate"] as? String ?: throw IllegalArgumentException("startDate is required")),
            endDate = LocalDate.parse(request["endDate"] as? String ?: throw IllegalArgumentException("endDate is required"))
        )
        val keyResult = personalObjectiveUseCase.addKeyResult(input)
        return ResponseEntity.ok(PersonalObjectiveMapper.toKeyResultResponse(keyResult))
    }

    @PutMapping("/{objectiveId}/status")
    fun updateStatus(
        @PathVariable objectiveId: String,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Unit> {
        val input = PersonalObjectiveMapper.toUpdateStatusInput(
            objectiveId = objectiveId,
            status = request["status"] ?: throw IllegalArgumentException("status is required")
        )
        personalObjectiveUseCase.updateObjectiveStatus(input)
        return ResponseEntity.ok().build()
    }
}
