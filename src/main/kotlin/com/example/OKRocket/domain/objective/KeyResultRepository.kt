package com.example.OKRocket.domain.objective

interface KeyResultRepository {
    fun findById(id: String): KeyResult?
    fun findByObjectiveId(objectiveId: String): List<KeyResult>
    fun save(keyResult: KeyResult): KeyResult
    fun delete(id: String)
}
