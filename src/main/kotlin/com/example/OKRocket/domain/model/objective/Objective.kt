package com.example.OKRocket.domain.model.objective

import com.example.OKRocket.domain.model.keyresult.KeyResult
import com.example.OKRocket.domain.model.reflection.Reflection
import com.example.OKRocket.domain.model.status.Status
import java.time.LocalDate
import java.util.UUID

class Objective(
    val id: String,
    val title: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val keyResults: List<KeyResult>,
    val reflection: Reflection?,
    val status: Status
) {
    companion object {
        /**
         * 新しいObjectiveを作成する
         */
        fun create(
            title: String,
            description: String,
            startDate: LocalDate,
            endDate: LocalDate,
            keyResults: List<KeyResult> = emptyList()
        ): Objective {
            return Objective(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description,
                startDate = startDate,
                endDate = endDate,
                keyResults = keyResults,
                reflection = null,
                status = Status.PENDING
            )
        }
    }
}
