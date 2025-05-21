package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.user.User

interface UserRepository {
    fun findById(id: String): User?
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun delete(id: String)
}
