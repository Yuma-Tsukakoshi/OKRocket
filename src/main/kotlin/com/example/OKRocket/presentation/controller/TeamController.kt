package com.example.OKRocket.presentation.controller

import com.example.OKRocket.application.usecase.teammanagement.TeamManagementUseCase
import com.example.OKRocket.domain.team.Team
import com.example.OKRocket.domain.team.TeamMembership
import com.example.OKRocket.presentation.dto.CreateTeamRequest
import com.example.OKRocket.presentation.dto.CreateTeamResponse
import com.example.OKRocket.presentation.dto.TeamMemberRequest
import com.example.OKRocket.presentation.dto.TeamMemberResponse
import com.example.OKRocket.presentation.dto.TeamResponse
import com.example.OKRocket.presentation.mapper.TeamMapper
import com.example.OKRocket.usecase.teammanagement.RemoveMemberInput
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/teams")
class TeamController(
    private val teamManagementUseCase: TeamManagementUseCase,
    private val teamMapper: TeamMapper
) {
    @PostMapping
    fun createTeam(@RequestBody request: CreateTeamRequest): CreateTeamResponse {
        val input = teamMapper.toCreateTeamInput(request)
        val output = teamManagementUseCase.createTeam(input)
        return teamMapper.toCreateTeamResponse(output)
    }

    @PostMapping("/{teamId}/members")
    fun addTeamMember(
        @PathVariable teamId: String,
        @RequestBody request: TeamMemberRequest
    ): CreateTeamResponse {
        val input = teamMapper.toAddTeamMemberInput(teamId, request)
        val output = teamManagementUseCase.addTeamMember(input)
        return teamMapper.toCreateTeamResponse(output)
    }

    @DeleteMapping("/{teamId}/members/{userId}")
    fun removeMember(
        @PathVariable teamId: String,
        @PathVariable userId: String,
        @RequestHeader("X-User-ID") operatorId: String
    ): ResponseEntity<Unit> {
        teamManagementUseCase.removeMember(
            RemoveMemberInput(
                teamId = teamId,
                userId = userId,
                operatorId = operatorId
            )
        )
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getTeam(@PathVariable id: String): CreateTeamResponse {
        val team = teamManagementUseCase.getTeam(id)
        return teamMapper.toCreateTeamResponse(team)
    }

    private fun Team.toResponse() = TeamResponse(
        id = id,
        name = name,
        description = description
    )

    private fun TeamMembership.toResponse() = TeamMemberResponse(
        id = id,
        userId = userId,
        role = role.name,
        joinedAt = joinedAt.format(DateTimeFormatter.ISO_DATE_TIME)
    )
}
