package com.kweku.armah.core.presentation.data

data class QuestionsUi(
    val id: Int = 0,
    val question: String = "",
    val answers: List<AnswerUi> = emptyList(),
    val correctAnswers: List<AnswerUi> = emptyList(),
    val selectedAnswers: MutableSet<AnswerUi> = mutableSetOf(),
)

data class AnswerUi(val data: String = "")
