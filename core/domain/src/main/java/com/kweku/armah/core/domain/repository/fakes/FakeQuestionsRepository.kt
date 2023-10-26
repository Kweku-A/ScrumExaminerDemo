package com.kweku.armah.core.domain.repository.fakes

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository

class FakeQuestionsRepository : QuestionsRepository {

    var response: MutableList<Question> = mutableListOf()
    override fun getQuestionsFromDatabase(): List<Question> {
      return response
    }

    override suspend fun deleteAllQuestions() {
        response.clear()
    }

    override suspend fun selectRandomQuestions(numberOfQuestions: Int): List<Question> {
        return response.take(numberOfQuestions)
    }

    override suspend fun insertQuestionsIntoData(questionGroup: List<Question>) {
       response.addAll(questionGroup)
    }
}
