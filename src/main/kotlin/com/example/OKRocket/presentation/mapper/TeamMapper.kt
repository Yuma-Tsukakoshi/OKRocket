package com.example.OKRocket.presentation.mapper

import com.example.OKRocket.domain.team.Role
import com.example.OKRocket.domain.team.Team
import com.example.OKRocket.presentation.dto.CreateTeamRequest
import com.example.OKRocket.presentation.dto.CreateTeamResponse
import com.example.OKRocket.presentation.dto.TeamMemberRequest
import com.example.OKRocket.usecase.teammanagement.AddTeamMemberInput
import com.example.OKRocket.usecase.teammanagement.CreateTeamInput
import com.example.OKRocket.usecase.teammanagement.CreateTeamOutput
import org.springframework.stereotype.Component

@Component
class TeamMapper {
    fun toCreateTeamInput(request: CreateTeamRequest): CreateTeamInput {
        return CreateTeamInput(
            name = request.name,
            description = request.description
        )
    }

    fun toAddTeamMemberInput(teamId: String, request: TeamMemberRequest): AddTeamMemberInput {
        return AddTeamMemberInput(
            teamId = teamId,
            userId = request.userId,
            role = Role.valueOf(request.role)
        )
    }

    fun toCreateTeamResponse(output: CreateTeamOutput): CreateTeamResponse {
        return CreateTeamResponse(
            id = output.team.id,
            name = output.team.name,
            description = output.team.description,
            members = output.team.members.map { member ->
                CreateTeamResponse.TeamMember(
                    userId = member.userId,
                    role = member.role.name
                )
            }
        )
    }

    fun toCreateTeamResponse(team: Team): CreateTeamResponse {
        return CreateTeamResponse(
            id = team.id,
            name = team.name,
            description = team.description,
            members = team.members.map { member ->
                CreateTeamResponse.TeamMember(
                    userId = member.userId,
                    role = member.role.name
                )
            }
        )
    }
}
