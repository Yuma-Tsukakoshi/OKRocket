package com.example.OKRocket.domain.user

import com.example.OKRocket.domain.user.User

interface UserRepository {
  fun findById(id: Long): User?
  fun save(user: User): User
  fun delete(user: User)
}

