package com.example.OKRocket.domain

import java.time.LocalDateTime

data class TeamMembership(
    val id: String,
    val userId: String,
    val teamId: String,
    val role: Role,
    val joinedAt: LocalDateTime
)
