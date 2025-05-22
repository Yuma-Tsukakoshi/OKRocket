package com.example.OKRocket.infrastructure.persistence.jooq

import com.example.OKRocket.domain.model.objective.Objective
import com.example.OKRocket.domain.repository.ObjectiveRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class ObjectiveRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) : ObjectiveRepository {

    private val rowMapper = RowMapper { rs, _ ->
        Objective(
            id = rs.getString("id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            startDate = rs.getDate("start_date").toLocalDate(),
            endDate = rs.getDate("end_date").toLocalDate(),
            keyResults = emptyList(), // TODO: KeyResultの取得を実装
            reflection = null, // TODO: Reflectionの取得を実装
            status = com.example.OKRocket.domain.model.status.Status.valueOf(rs.getString("status"))
        )
    }

    override fun findById(id: String): Objective? {
        val sql = "SELECT * FROM objective WHERE id = ?"
        return jdbcTemplate.query(sql, rowMapper, id).firstOrNull()
    }

    override fun findByUserId(userId: String): List<Objective> {
        val sql = "SELECT * FROM objective WHERE user_id = ?"
        return jdbcTemplate.query(sql, rowMapper, userId)
    }

    override fun findByTeamId(teamId: String): List<Objective> {
        val sql = "SELECT * FROM objective WHERE team_id = ?"
        return jdbcTemplate.query(sql, rowMapper, teamId)
    }

    override fun save(objective: Objective): Objective {
        val sql = """
            INSERT INTO objective (id, title, description, start_date, end_date, status, user_id, team_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT (id) DO UPDATE
            SET title = ?, description = ?, start_date = ?, end_date = ?, status = ?, user_id = ?, team_id = ?
        """.trimIndent()

        jdbcTemplate.update(
            sql,
            objective.id,
            objective.title,
            objective.description,
            objective.startDate,
            objective.endDate,
            objective.status.name,
            null, // TODO: user_idの設定
            null, // TODO: team_idの設定
            objective.title,
            objective.description,
            objective.startDate,
            objective.endDate,
            objective.status.name,
            null, // TODO: user_idの設定
            null // TODO: team_idの設定
        )

        return objective
    }

    override fun delete(id: String) {
        val sql = "DELETE FROM objective WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }
}
