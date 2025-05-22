package com.example.OKRocket.domain.repository

import com.example.OKRocket.domain.model.team.Team

interface TeamRepository {
    fun findById(id: String): Team?
    fun save(team: Team): Team
    fun delete(id: String)
}
