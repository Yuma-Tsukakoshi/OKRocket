package com.example.OKRocket.domain

enum class Role {
    ADMIN,
    READER,
    MEMBER;

    fun canExecute(requiredRole: Role): Boolean {
        return when (this) {
            ADMIN -> true
            READER -> requiredRole == READER || requiredRole == MEMBER
            MEMBER -> requiredRole == MEMBER
        }
    }
}
