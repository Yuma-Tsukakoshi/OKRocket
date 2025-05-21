package com.example.OKRocket.domain.user

data class User(
    val id: String,
    val email: String,
    val name: String,
    val profile: UserProfile
)

data class UserProfile(
    val id: String,
    val userId: String,
    val bio: String?,
    val avatarUrl: String?
)
