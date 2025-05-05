package com.example.OKRocket.domain.team

import com.example.OKRocket.domain.team.Team


interface TeamRepository {
  fun findById(id: Long): Team?
  fun save(team: Team): Team
  fun delete(team: Team)
}

