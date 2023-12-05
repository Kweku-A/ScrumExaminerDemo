package com.kweku.armah.core.utilities.files.fake

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.utilities.files.FileHelper
import javax.inject.Inject

class FakeFileHelperImpl @Inject constructor() :
    FileHelper {

    override suspend fun getQuestionsFromFile(resourceId: Int): List<Question> {
        return fakeListOfQuestions
    }

    companion object {

        val fakeAnswer1 = Answer(data = "Fake answer 1")
        val fakeAnswer2 = Answer(data = "Fake answer 2")
        val fakeAnswer3 = Answer(data = "Fake answer 3")
        val fakeAnswer4 = Answer(data = "Fake answer 4")
        val fakeAnswer5 = Answer(data = "Fake answer 5")

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
            Question(
                id = 1,
                question = "Fake question 1",
                answers = fakeAnswerList,
                correctAnswers = fakeCorrectAnswerList,
                selectedAnswers = emptyList(),
            ),
            Question(
                id = 2,
                question = "Fake question 2",
                answers = fakeAnswerList,
                correctAnswers = fakeCorrectAnswerList,
                selectedAnswers = emptyList(),
            ),
            Question(
                id = 3,
                question = "Fake question 3",
                answers = fakeAnswerList,
                correctAnswers = fakeCorrectAnswerList,
                selectedAnswers = emptyList(),
            ),
            Question(
                id = 4,
                question = "Fake question 4",
                answers = fakeAnswerList,
                correctAnswers = fakeCorrectAnswerList,
                selectedAnswers = emptyList(),
            ),
            Question(
                id = 5,
                question = "Fake question 5",
                answers = fakeAnswerList,
                correctAnswers = fakeCorrectAnswerList,
                selectedAnswers = emptyList(),
            ),
        )
    }
}
