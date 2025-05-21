package com.example.OKRocket.domain.objective

interface ReflectionRepository {
    fun findById(id: String): Reflection?
    fun findByObjectiveId(objectiveId: String): List<Reflection>
    fun save(reflection: Reflection): Reflection
    fun delete(id: String)
}
