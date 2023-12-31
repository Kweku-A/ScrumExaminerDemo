package com.kweku.armah.pspo.data.repository

import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.pspo.data.database.dao.PspoQuizDao
import com.kweku.armah.pspo.data.database.enitities.PspoQuizEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Based on SRP, this can be split into two separate repositories
// i.e. QuizRepository and QuestionsRepository but keeping it as such for now
class PspoQuizQuestionsRepositoryImpl @Inject constructor(
    private val pspoQuizDao: PspoQuizDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : QuizQuestionsRepository {

    override suspend fun saveSelectedQuizQuestionsToDatabase(quizQuestions: List<Question>) {
        val quizEntities = quizQuestions.map { item ->
            PspoQuizEntity(
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

        pspoQuizDao.insertAll(quizEntities)
    }

    override suspend fun getQuiz(): List<Question> {
        return withContext(dispatcher) {
            pspoQuizDao.getAll().map { quizEntity ->
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
    }

    override suspend fun deleteAllQuiz() {
        pspoQuizDao.deleteAll()
    }

    override suspend fun updateQuizWithSelectedAnswers(question: Question) {
        val pspoQuizEntity = PspoQuizEntity(
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
        withContext(dispatcher) {
            pspoQuizDao.updateQuizWithSelectedAnswers(
                pspoQuizEntity.id,
                pspoQuizEntity.selectedAnswers,
            )
        }
    }
}
