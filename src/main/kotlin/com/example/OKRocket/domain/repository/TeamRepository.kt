package com.example.OKRocket.domain.model.team

interface TeamRepository {
    fun findById(id: Long): Team?
    fun save(team: Team): Team
    fun delete(team: Team)
}
