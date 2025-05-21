package com.example.OKRocket.usecase.teammanagement

data class CreateTeamInput(
    val name: String,
    val description: String
)

data class CreateTeamOutput(
    val id: String,
    val name: String,
    val description: String
)

data class AddMemberInput(
    val userId: String,
    val role: String
)

data class TeamMemberOutput(
    val id: String,
    val userId: String,
    val role: String,
    val joinedAt: String
)
