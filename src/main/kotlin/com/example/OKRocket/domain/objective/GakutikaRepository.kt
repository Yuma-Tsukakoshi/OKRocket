package com.example.OKRocket.domain.objective

interface GakutikaRepository {
    fun findById(id: String): Gakutika?
    fun findByReflectionId(reflectionId: String): List<Gakutika>
    fun save(gakutika: Gakutika): Gakutika
    fun delete(id: String)
}
