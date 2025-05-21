package com.example.OKRocket.domain.model.teammembership

import com.example.OKRocket.domain.model.role.Role
import java.time.LocalDateTime
class TeamMembership(
    val id: String,
    val userId: String,
    val teamId: String,
    val role: Role,
    val joinedAt: LocalDateTime
)
