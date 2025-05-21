package com.example.OKRocket.usecase.teammanagement

import com.example.OKRocket.domain.Role
import com.example.OKRocket.domain.Team
import com.example.OKRocket.domain.TeamMembership

data class CreateTeamInput(
    val name: String,
    val description: String,
    val creatorId: String
)

data class CreateTeamOutput(
    val team: Team,
    val membership: TeamMembership
)

data class AddMemberInput(
    val teamId: String,
    val userId: String,
    val role: Role,
    val operatorId: String
)

data class RemoveMemberInput(
    val teamId: String,
    val userId: String,
    val operatorId: String
)
