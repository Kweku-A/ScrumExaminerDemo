package com.kweku.armah.psm.data.database.enitities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kweku.armah.core.domain.model.Answer

@Entity(tableName = "psm_quiz")
data class PsmQuizEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var question: String,
    var answers: List<Answer>,
    var correctAnswers: List<Answer>,
    var selectedAnswers: List<Answer>,
)
