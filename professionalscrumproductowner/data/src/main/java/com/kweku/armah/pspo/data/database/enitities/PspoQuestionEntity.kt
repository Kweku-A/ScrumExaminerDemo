package com.kweku.armah.pspo.data.database.enitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kweku.armah.core.domain.model.Answer

@Entity(tableName = "pspo_questions")
data class PspoQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "question")
    var question: String,
    var answers: List<Answer>,
    var correctAnswers: List<Answer>,
)
