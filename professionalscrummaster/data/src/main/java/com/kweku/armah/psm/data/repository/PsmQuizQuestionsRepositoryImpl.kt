package com.kweku.armah.psm.data.repository

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.psm.data.database.dao.PsmQuizDao
import com.kweku.armah.psm.data.database.enitities.PsmQuizEntity
import javax.inject.Inject

// Based on SRP, this can be split into two separate repositories
// i.e. QuizRepository and QuestionsRepository but keeping it as such for now
class PsmQuizQuestionsRepositoryImpl @Inject constructor(
    private val psmQuizDao: PsmQuizDao,

    ) :
    QuizQuestionsRepository {

    override suspend fun saveSelectedQuizQuestionsToDatabase(quizQuestions: List<Question>) {
        val quizEntities = quizQuestions.map { item ->
            PsmQuizEntity(
                id = item.id,
                question = item.question,
                answers = item.answers.map {
                    Answer(data = it.data)
                },
                correctAnswers = item.correctAnswers.map {
                    Answer(data = it.data)
                },
                selectedAnswers = mutableListOf(),
            )
        }

        psmQuizDao.insertAll(quizEntities)
    }

    override fun getQuiz(): List<Question> {
        return psmQuizDao.getAll().map { quizEntity ->
            Question(
                id = quizEntity.id,
                question = quizEntity.question,
                answers = quizEntity.answers.map {
                    Answer(data = it.data)
                },
                correctAnswers = quizEntity.correctAnswers.map {
                    Answer(data = it.data)
                },
                selectedAnswers = quizEntity.selectedAnswers.map {
                    Answer(data = it.data)
                },
            )
        }
    }

    override suspend fun deleteAllQuiz() {
        psmQuizDao.deleteAll()
    }

    override fun updateQuizWithSelectedAnswers(question: Question) {
        val psmQuizEntity = PsmQuizEntity(
            id = question.id,
            question = question.question,
            answers = question.answers.map {
                Answer(data = it.data)
            },
            correctAnswers = question.correctAnswers.map {
                Answer(data = it.data)
            },
            selectedAnswers = question.selectedAnswers.map {
                Answer(data = it.data)
            },
        )
        psmQuizDao.updateQuizWithSelectedAnswers(
            psmQuizEntity.id,
            psmQuizEntity.selectedAnswers,
        )
    }
}
