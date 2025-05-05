import com.example.OKRocket.domain.gakuchika.Gakuchika
import com.example.OKRocket.domain.keyresult.KeyResult
import com.example.OKRocket.domain.reflection.Reflection
import com.example.OKRocket.domain.status.Status
import java.time.LocalDateTime

class TeamObjective(
    /** 目標タイトル */
    val name: String,
    /** 目標の説明 */
    val description: String,
    /** 目標のステータス */
    val status: Status,
    /** 目標の開始日 */
    val startDate: LocalDateTime,
    /** 目標の終了日 */
    val endDate: LocalDateTime,
    /** 目標の成果指標 */
    val keyResults: MutableList<KeyResult>,
    /** 目標の振り返り */
    val reflection: Reflection?,
    /** 目標のガクチカ */
    val gakuchika: Gakuchika?
)
interface TeamObjectiveRepository {
    fun findById(id: Long): Objective?
    fun save(objective: Objective): Objective
    fun delete(objective: Objective)
    fun scheduleKeyResult(keyResult: KeyResult): Boolean
    fun notify(
        objective: Objective,
        keyResult: KeyResult,
        today: LocalDateTime
    ): Boolean
}

class TeamKeyResult(
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
