package com.example.OKRocket.infrastructure.persistence.jooq

import com.example.OKRocket.domain.team.Team
import com.example.OKRocket.domain.team.TeamRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class TeamRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) : TeamRepository {

    private val teamRowMapper = RowMapper { rs, _ ->
        Team(
            id = rs.getString("id"),
            name = rs.getString("name"),
            description = rs.getString("description")
        )
    }

    private val membershipRowMapper = RowMapper { rs, _ ->
        TeamMembership(
            id = rs.getString("id"),
            userId = rs.getString("user_id"),
            teamId = rs.getString("team_id"),
            role = Role.valueOf(rs.getString("role")),
            joinedAt = rs.getTimestamp("joined_at").toLocalDateTime()
        )
    }

    override fun findById(id: String): Team? {
        return jdbcTemplate.query(
            "SELECT * FROM teams WHERE id = ?",
            teamRowMapper,
            id
        ).firstOrNull()
    }

    override fun save(team: Team): Team {
        jdbcTemplate.update(
            """
            INSERT INTO teams (id, name, description)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE
            name = VALUES(name),
            description = VALUES(description)
            """,
            team.id,
            team.name,
            team.description
        )
        return team
    }

    override fun delete(id: String) {
        jdbcTemplate.update("DELETE FROM teams WHERE id = ?", id)
    }

    override fun findMembershipsByTeamId(teamId: String): List<TeamMembership> {
        return jdbcTemplate.query(
            "SELECT * FROM team_memberships WHERE team_id = ?",
            membershipRowMapper,
            teamId
        )
    }

    override fun saveMembership(membership: TeamMembership): TeamMembership {
        jdbcTemplate.update(
            """
            INSERT INTO team_memberships (id, user_id, team_id, role, joined_at)
            VALUES (?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
            role = VALUES(role)
            """,
            membership.id,
            membership.userId,
            membership.teamId,
            membership.role.name,
            membership.joinedAt
        )
        return membership
    }

    override fun deleteMembership(id: String) {
        jdbcTemplate.update("DELETE FROM team_memberships WHERE id = ?", id)
    }
}
