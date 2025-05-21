package com.example.OKRocket.domain.user

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profile: UserProfile
)

data class UserProfile(
    val bio: String,
    val avatarUrl: String?
)
