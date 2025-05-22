package com.example.OKRocket.domain.model.keyresult

import com.example.OKRocket.domain.model.status.Status
import java.time.LocalDate

class KeyResult(
    val id: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: Status
)
