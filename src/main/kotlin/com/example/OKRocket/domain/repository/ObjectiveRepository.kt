package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.gakuchika.Gakuchika
import com.example.OKRocket.domain.model.keyresult.KeyResult
import com.example.OKRocket.domain.model.objective.Objective
import com.example.OKRocket.domain.model.reflection.Reflection

interface ObjectiveRepository {
    fun findById(id: String): Objective?
    fun findByUserId(userId: String): List<Objective>
    fun findByTeamId(teamId: String): List<Objective>
    fun save(objective: Objective): Objective
    fun delete(id: String)
}

interface KeyResultRepository {
    fun findById(id: String): KeyResult?
    fun findByObjectiveId(objectiveId: String): List<KeyResult>
    fun save(keyResult: KeyResult): KeyResult
    fun delete(id: String)
}

interface ReflectionRepository {
    fun findById(id: String): Reflection?
    fun findByObjectiveId(objectiveId: String): List<Reflection>
    fun save(reflection: Reflection): Reflection
    fun delete(id: String)
}

interface GakuchikaRepository {
    fun findById(id: String): Gakuchika?
    fun findByReflectionId(reflectionId: String): List<Gakuchika>
    fun save(gakuchika: Gakuchika): Gakuchika
    fun delete(id: String)
}
