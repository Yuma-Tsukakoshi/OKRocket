package com.example.OKRocket.presentation.dto

data class CreateTeamRequest(
    val name: String,
    val description: String
)

data class CreateTeamResponse(
    val id: String,
    val name: String,
    val description: String,
    val members: List<TeamMember>
) {
    data class TeamMember(
        val userId: String,
        val role: String
    )
}

data class TeamMemberRequest(
    val userId: String,
    val role: String
)

data class TeamResponse(
    val id: String,
    val name: String,
    val description: String
)

data class TeamMemberResponse(
    val id: String,
    val userId: String,
    val role: String,
    val joinedAt: String
)
