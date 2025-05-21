package com.example.OKRocket.domain.team

interface TeamRepository {
    fun findById(id: String): Team?
    fun findByUserId(userId: String): List<Team>
    fun save(team: Team): Team
    fun delete(id: String)
}
