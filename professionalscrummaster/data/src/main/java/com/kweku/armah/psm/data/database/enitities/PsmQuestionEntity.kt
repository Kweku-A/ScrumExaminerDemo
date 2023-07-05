package com.kweku.armah.psm.data.database.enitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kweku.armah.core.domain.model.Answer

@Entity(tableName = "psm_questions")
data class PsmQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "question")
    var question: String,
    var answers: List<Answer>,
    var correctAnswers: List<Answer>,
)
