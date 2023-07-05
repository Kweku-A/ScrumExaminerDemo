package com.kweku.armah.pspo.data.repository

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.pspo.data.database.dao.PspoQuestionsDao
import com.kweku.armah.pspo.data.database.enitities.PspoQuestionEntity
import javax.inject.Inject
import kotlin.random.Random

class PspoQuestionsRepositoryImpl @Inject constructor(private val pspoQuestionsDao: PspoQuestionsDao) :
    QuestionsRepository {

    override fun getQuestionsFromDatabase(): List<Question> {
        return pspoQuestionsDao.getAll().map { questionEntity ->
            Question(
                id = questionEntity.id,
                question = questionEntity.question,
                answers = questionEntity.answers.map {
                    Answer(data = it.data)
                },
                correctAnswers = questionEntity.correctAnswers.filter {
                    it.data.trim().isNotEmpty()
                }.map {
                    Answer(data = it.data)
                },
                selectedAnswers = emptyList(),
            )
        }
    }

    override suspend fun deleteAllQuestions() {
        pspoQuestionsDao.deleteAll()
    }

    override suspend fun insertQuestionsIntoData(questionGroup: List<Question>) {
        val entities = questionGroup.map { item ->
            PspoQuestionEntity(
                id = 0,
                question = item.question,
                answers = item.answers.map {
                    Answer(data = it.data)
                },
                correctAnswers = item.correctAnswers.map {
                    Answer(data = it.data)
                },
            )
        }
        pspoQuestionsDao.insertAll(entities)
    }

    override suspend fun selectRandomQuestions(numberOfQuestions: Int): List<Question> {
        val questions: MutableList<PspoQuestionEntity> = pspoQuestionsDao.getAll().toMutableList()
        val selectedEightyQuestions: MutableSet<PspoQuestionEntity> = mutableSetOf()

        while (selectedEightyQuestions.size < numberOfQuestions) {
            val index: Int = Random.nextInt(questions.size)
            selectedEightyQuestions.add(questions.removeAt(index))
        }

        return selectedEightyQuestions.toList().map { questionEntity ->
            Question(
                id = questionEntity.id,
                question = questionEntity.question,
                answers = questionEntity.answers.map {
                    Answer(data = it.data)
                },
                correctAnswers = questionEntity.correctAnswers.map {
                    Answer(data = it.data)
                },
                selectedAnswers = emptyList(),
            )
        }
    }
}
