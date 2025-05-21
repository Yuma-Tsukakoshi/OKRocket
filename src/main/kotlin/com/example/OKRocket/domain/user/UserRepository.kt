package com.example.OKRocket.domain.user

interface UserRepository {
    fun findById(id: String): User?
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun delete(id: String)
}
