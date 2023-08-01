package com.kweku.armah.core.domain.model

data class Question(
    val id: Int = 0,
    val question: String = "",
    val answers: List<Answer> = emptyList(),
    val correctAnswers: List<Answer> = emptyList(),
    val selectedAnswers: List<Answer> = emptyList(),
)

data class Answer(val data: String = "")
