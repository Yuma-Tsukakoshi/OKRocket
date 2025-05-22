package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.teammembership.TeamMembership

interface TeamMembershipsRepository {
    fun findMembershipsByTeamId(teamId: String): List<TeamMembership>
    fun saveMembership(membership: TeamMembership): TeamMembership
    fun deleteMembership(id: String)
}
