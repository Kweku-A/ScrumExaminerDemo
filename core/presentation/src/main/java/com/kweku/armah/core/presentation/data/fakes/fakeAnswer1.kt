package com.kweku.armah.core.presentation.data.fakes

import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi

val fakeAnswer1 = AnswerUi(data = "Fake answer 1")
val fakeAnswer2 = AnswerUi(data = "Fake answer 2")
val fakeAnswer3 = AnswerUi(data = "Fake answer 3")
val fakeAnswer4 = AnswerUi(data = "Fake answer 4")
val fakeAnswer5 = AnswerUi(data = "Fake answer 5")

val fakeAnswerList = listOf(
    fakeAnswer1,
    fakeAnswer2,
    fakeAnswer3,
    fakeAnswer4,
    fakeAnswer5,
)

val fakeCorrectAnswerList = listOf(
    fakeAnswer1,
    fakeAnswer5,
)

val fakeListOfQuestions = listOf(
    QuestionsUi(
        id = 1,
        question = "Fake question 1",
        answers = fakeAnswerList,
        correctAnswers = fakeCorrectAnswerList,
        selectedAnswers = mutableSetOf(),
    ),
    QuestionsUi(
        id = 2,
        question = "Fake question 2",
        answers = fakeAnswerList,
        correctAnswers = fakeCorrectAnswerList,
        selectedAnswers = mutableSetOf(),
    ),
    QuestionsUi(
        id = 3,
        question = "Fake question 3",
        answers = fakeAnswerList,
        correctAnswers = fakeCorrectAnswerList,
        selectedAnswers = mutableSetOf(),
    ),
    QuestionsUi(
        id = 4,
        question = "Fake question 4",
        answers = fakeAnswerList,
        correctAnswers = fakeCorrectAnswerList,
        selectedAnswers = mutableSetOf(),
    ),
    QuestionsUi(
        id = 5,
        question = "Fake question 5",
        answers = fakeAnswerList,
        correctAnswers = fakeCorrectAnswerList,
        selectedAnswers = mutableSetOf(),
    ),
)
