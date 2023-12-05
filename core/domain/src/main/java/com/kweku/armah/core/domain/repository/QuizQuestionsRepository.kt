package com.kweku.armah.core.domain.repository

import com.kweku.armah.core.domain.model.Question

interface QuizQuestionsRepository {
    suspend fun saveSelectedQuizQuestionsToDatabase(quizQuestions: List<Question>)

    suspend fun deleteAllQuiz()
    suspend fun updateQuizWithSelectedAnswers(question: Question)
    suspend fun getQuiz(): List<Question>
}
