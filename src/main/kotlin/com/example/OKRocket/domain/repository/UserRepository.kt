package com.example.OKRocket.domain.model.user

interface UserRepository {
    fun findById(id: Long): User?
    fun save(user: User): User
    fun delete(user: User)
}
