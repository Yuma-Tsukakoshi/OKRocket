package com.example.OKRocket.application.usecase.teammanagement

import com.example.OKRocket.domain.team.Team
import com.example.OKRocket.domain.team.TeamMembership
import com.example.OKRocket.domain.team.TeamRepository
import java.time.LocalDateTime
import java.util.UUID

class TeamManagementUseCase(
    private val teamRepository: TeamRepository
) {
    fun createTeam(name: String, description: String, creatorId: String): Team {
        val team = Team(
            id = generateId(),
            name = name,
            description = description
        )
        val savedTeam = teamRepository.save(team)

        // 作成者を管理者として追加
        val membership = TeamMembership(
            id = generateId(),
            userId = creatorId,
            teamId = savedTeam.id,
            role = Role.ADMIN,
            joinedAt = LocalDateTime.now()
        )
        teamRepository.saveMembership(membership)

        return savedTeam
    }

    fun addMember(teamId: String, userId: String, role: Role, operatorId: String) {
        val operatorMembership = teamRepository.findMembershipsByTeamId(teamId)
            .find { it.userId == operatorId }
            ?: throw IllegalStateException("Operator is not a member of the team")

        if (!operatorMembership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Operator does not have permission to add members")
        }

        val membership = TeamMembership(
            id = generateId(),
            userId = userId,
            teamId = teamId,
            role = role,
            joinedAt = LocalDateTime.now()
        )
        teamRepository.saveMembership(membership)
    }

    fun removeMember(teamId: String, userId: String, operatorId: String) {
        val operatorMembership = teamRepository.findMembershipsByTeamId(teamId)
            .find { it.userId == operatorId }
            ?: throw IllegalStateException("Operator is not a member of the team")

        if (!operatorMembership.role.canExecute(Role.ADMIN)) {
            throw IllegalStateException("Operator does not have permission to remove members")
        }

        val membership = teamRepository.findMembershipsByTeamId(teamId)
            .find { it.userId == userId }
            ?: throw IllegalStateException("User is not a member of the team")

        teamRepository.deleteMembership(membership.id)
    }

    private fun generateId(): String = java.util.UUID.randomUUID().toString()
}
