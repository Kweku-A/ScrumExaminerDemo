package com.kweku.armah.core.domain.model

data class Question(
    val id: Int = 0,
    val question: String,
    val answers: List<Answer>,
    val correctAnswers: List<Answer>,
    val selectedAnswers: List<Answer>,
)

data class Answer(val data: String = "")
