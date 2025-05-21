package com.example.OKRocket.domain

interface TeamRepository {
    fun findById(id: String): Team?
    fun save(team: Team): Team
    fun delete(id: String)
    fun findMembershipsByTeamId(teamId: String): List<TeamMembership>
    fun saveMembership(membership: TeamMembership): TeamMembership
    fun deleteMembership(id: String)
}
