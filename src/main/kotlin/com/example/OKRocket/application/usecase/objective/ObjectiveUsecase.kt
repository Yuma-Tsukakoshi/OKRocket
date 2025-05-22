package com.example.OKRocket.application.usecase.objective

import com.example.OKRocket.domain.model.objective.Objective
import com.example.OKRocket.domain.repository.ObjectiveRepository
import org.springframework.stereotype.Service

@Service
class ObjectiveUsecase(
    private val objectiveRepository: ObjectiveRepository
) {
    /**
     * Objectiveを取得する
     */
    fun getObjective(id: String): Objective? {
        return objectiveRepository.findById(id)
    }

    /**
     * ユーザーに関連するObjectiveを取得する
     */
    fun getObjectivesByUserId(userId: String): List<Objective> {
        return objectiveRepository.findByUserId(userId)
    }

    /**
     * チームに関連するObjectiveを取得する
     */
    fun getObjectivesByTeamId(teamId: String): List<Objective> {
        return objectiveRepository.findByTeamId(teamId)
    }

    /**
     * Objectiveを保存する
     */
    fun saveObjective(objective: Objective): Objective {
        return objectiveRepository.save(objective)
    }

    /**
     * Objectiveを削除する
     */
    fun deleteObjective(id: String) {
        objectiveRepository.delete(id)
    }
}
