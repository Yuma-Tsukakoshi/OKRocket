package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.objective.Objective
interface ObjectiveRepository {
    fun findById(id: String): Objective?
    fun findByUserId(userId: String): List<Objective>
    fun findByTeamId(teamId: String): List<Objective>
    fun save(objective: Objective): Objective
    fun delete(id: String)
}
