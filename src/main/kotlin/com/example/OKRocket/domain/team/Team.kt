package com.example.OKRocket.domain.team

data class Team(
    val id: String,
    val name: String,
    val description: String,
    val members: List<TeamMember>
)

data class TeamMember(
    val userId: String,
    val role: Role
)

enum class Role {
    OWNER,
    ADMIN,
    MEMBER
}
