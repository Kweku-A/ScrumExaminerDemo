package com.kweku.armah.core.domain.repository.fakes

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource

class FakeQuestionsFileDataSource : QuestionsFileDataSource {

    var response: List<Question> = fakeListOfQuestions
    override suspend fun getQuestionsFromFileIntoDatabase(resourceId: Int): List<Question> {
        return response
    }

    companion object {
        val fakeListOfQuestions = listOf(
            Question(
                id = 1,
                question = "a",
                answers = listOf(Answer(data = "b"), Answer(data = "c")),
                correctAnswers = listOf(Answer(data = "d"), Answer(data = "e")),
                selectedAnswers = listOf(Answer(data = "d")),
            ),
        )
    }
}
