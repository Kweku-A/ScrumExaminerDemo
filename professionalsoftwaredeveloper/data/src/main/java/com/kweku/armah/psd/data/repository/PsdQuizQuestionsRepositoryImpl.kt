package com.kweku.armah.psd.data.repository

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.psd.data.database.dao.PsdQuizDao
import com.kweku.armah.psd.data.database.enitities.PsdQuizEntity
import javax.inject.Inject

// Based on SRP, this can be split into two separate repositories
// i.e. QuizRepository and QuestionsRepository but keeping it as such for now
class PsdQuizQuestionsRepositoryImpl @Inject constructor(
    private val psdQuizDao: PsdQuizDao,

) :
    QuizQuestionsRepository {

    override suspend fun saveSelectedQuizQuestionsToDatabase(quizQuestions: List<Question>) {
        val quizEntities = quizQuestions.map { item ->
            PsdQuizEntity(
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

        psdQuizDao.insertAll(quizEntities)
    }

    override fun getQuiz(): List<Question> {
        return psdQuizDao.getAll().map { quizEntity ->
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
        psdQuizDao.deleteAll()
    }

    override fun updateQuizWithSelectedAnswers(question: Question) {
        val psdQuizEntity = PsdQuizEntity(
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
        psdQuizDao.updateQuizWithSelectedAnswers(
            psdQuizEntity.id,
            psdQuizEntity.selectedAnswers,
        )
    }
}
