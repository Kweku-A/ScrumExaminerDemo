package com.kweku.armah.psm.data.repository

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.psm.data.database.dao.PsmQuestionsDao
import com.kweku.armah.psm.data.database.enitities.PsmQuestionEntity
import javax.inject.Inject
import kotlin.random.Random

class PsmQuestionsRepositoryImpl @Inject constructor(private val psmQuestionsDao: PsmQuestionsDao) :
    QuestionsRepository {

    override fun getQuestionsFromDatabase(): List<Question> {
        return psmQuestionsDao.getAll().map { questionEntity ->
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
        psmQuestionsDao.deleteAll()
    }

    override suspend fun insertQuestionsIntoData(questionGroup: List<Question>) {
        val entities = questionGroup.map { item ->
            PsmQuestionEntity(
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
        psmQuestionsDao.insertAll(entities)
    }

    override suspend fun selectRandomQuestions(numberOfQuestions: Int): List<Question> {
        val questions: MutableList<PsmQuestionEntity> = psmQuestionsDao.getAll().toMutableList()
        val selectedEightyQuestions: MutableSet<PsmQuestionEntity> = mutableSetOf()

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
