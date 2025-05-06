package com.example.OKRocket.domain.model.keyresult
import com.example.OKRocket.domain.model.status.Status
import java.time.LocalDateTime

class KeyResult(
    /** 成果指標のタイトル */
    val name: String,
    /** 成果指標の説明 */
    val description: String,
    /** 成果指標のステータス */
    val status: Status,
    /** 成果指標の開始日 */
    val startDate: LocalDateTime,
    /** 成果指標の終了日 */
    val endDate: LocalDateTime
)
