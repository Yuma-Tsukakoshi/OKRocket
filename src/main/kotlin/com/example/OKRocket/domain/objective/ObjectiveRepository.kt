package com.example.OKRocket.domain.objective

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

interface GakutikaRepository {
    fun findById(id: String): Gakutika?
    fun findByReflectionId(reflectionId: String): List<Gakutika>
    fun save(gakutika: Gakutika): Gakutika
    fun delete(id: String)
}
