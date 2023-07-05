package com.kweku.armah.core.domain.repository

import com.kweku.armah.core.domain.model.Question

interface QuestionsRepository {
    fun getQuestionsFromDatabase(): List<Question>
    suspend fun deleteAllQuestions()
    suspend fun selectRandomQuestions(numberOfQuestions: Int): List<Question>
    suspend fun insertQuestionsIntoData(questionGroup: List<Question>)
}
