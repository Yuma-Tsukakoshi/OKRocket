package com.example.OKRocket.domain.user

import java.time.LocalDateTime

class User(
    /** ユーザーの名前 */
    val name: String,
    /** 大学 */
    val university: String,
    /** 学部 */
    val department: String,
    /** 学年 */
    val grade: String,
    /** 誕生日 */
    val birthday: LocalDateTime,
    /** ユーザーのメールアドレス */
    val email: String,
    /** ユーザーのパスワード */
    val password: String
)

interface UserRepository {
    fun findById(id: Long): User?
    fun save(user: User): User
    fun delete(user: User)
}
