package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.team.Team
import com.example.OKRocket.domain.model.teammembership.TeamMembership

interface TeamRepository {
    fun findById(id: String): Team?
    fun save(team: Team): Team
    fun delete(id: String)
    fun findMembershipsByTeamId(teamId: String): List<TeamMembership>
    fun saveMembership(membership: TeamMembership): TeamMembership
    fun deleteMembership(id: String)
}
