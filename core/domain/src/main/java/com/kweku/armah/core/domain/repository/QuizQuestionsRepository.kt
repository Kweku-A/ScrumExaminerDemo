package com.kweku.armah.core.domain.repository

import com.kweku.armah.core.domain.model.Question

interface QuizQuestionsRepository {
    suspend fun saveSelectedQuizQuestionsToDatabase(quizQuestions: List<Question>)

    suspend fun deleteAllQuiz()
    fun updateQuizWithSelectedAnswers(question: Question)
    fun getQuiz(): List<Question>
}
