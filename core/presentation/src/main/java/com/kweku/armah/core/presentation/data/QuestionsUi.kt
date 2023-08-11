package com.kweku.armah.core.presentation.data

import javax.annotation.concurrent.Immutable

@Immutable
data class QuestionsUi(
    val id: Int = 0,
    val question: String = "",
    val answers: List<AnswerUi> = emptyList(),
    val correctAnswers: List<AnswerUi> = emptyList(),
    val selectedAnswers: MutableSet<AnswerUi> = mutableSetOf(),
)

@Immutable
data class AnswerUi(val data: String = "")
