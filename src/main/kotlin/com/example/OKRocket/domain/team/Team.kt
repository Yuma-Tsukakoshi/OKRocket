package com.example.OKRocket.domain.team

import com.example.OKRocket.domain.user.User

class Team(
    /** チーム名 */
    val name: String,
    /** チームの説明 */
    val description: String,
    /** チームのメンバー */
    val members: MutableList<User>
)

interface TeamRepository {
    fun findById(id: Long): Team?
    fun save(team: Team): Team
    fun delete(team: Team)
}
