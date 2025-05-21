package com.example.OKRocket.application.usecase.personalobjective

import com.example.OKRocket.domain.objective.KeyResultRepository
import com.example.OKRocket.domain.objective.Objective
import com.example.OKRocket.domain.objective.ObjectiveRepository
import com.example.OKRocket.domain.objective.ObjectiveType
import com.example.OKRocket.domain.objective.Status
import com.example.OKRocket.usecase.personalobjective.CreatePersonalObjectiveInput
import com.example.OKRocket.usecase.personalobjective.CreatePersonalObjectiveOutput
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonalObjectiveUseCase(
    private val objectiveRepository: ObjectiveRepository,
    private val keyResultRepository: KeyResultRepository
) {
    fun createObjective(input: CreatePersonalObjectiveInput): CreatePersonalObjectiveOutput {
        val objective = Objective(
            id = generateId(),
            title = input.title,
            description = input.description,
            startDate = input.startDate,
            endDate = input.endDate,
            status = Status.NOT_STARTED,
            type = ObjectiveType.PERSONAL
        )

        val savedObjective = objectiveRepository.save(objective)
        return CreatePersonalObjectiveOutput(savedObjective)
    }

    fun getObjective(id: String): Objective {
        return objectiveRepository.findById(id) ?: throw IllegalArgumentException("Objective not found")
    }

    private fun generateId(): String = UUID.randomUUID().toString()
}
