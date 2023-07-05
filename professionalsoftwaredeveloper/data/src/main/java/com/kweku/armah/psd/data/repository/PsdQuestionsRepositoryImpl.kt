package com.kweku.armah.psd.data.repository

import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.psd.data.database.dao.PsdQuestionsDao
import com.kweku.armah.psd.data.database.enitities.PsdQuestionEntity
import javax.inject.Inject
import kotlin.random.Random

class PsdQuestionsRepositoryImpl @Inject constructor(private val psdQuestionsDao: PsdQuestionsDao) :
    QuestionsRepository {

    override fun getQuestionsFromDatabase(): List<Question> {
        return psdQuestionsDao.getAll().map { questionEntity ->
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
        psdQuestionsDao.deleteAll()
    }

    override suspend fun insertQuestionsIntoData(questionGroup: List<Question>) {
        val entities = questionGroup.map { item ->
            PsdQuestionEntity(
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
        psdQuestionsDao.insertAll(entities)
    }

    override suspend fun selectRandomQuestions(numberOfQuestions: Int): List<Question> {
        val questions: MutableList<PsdQuestionEntity> = psdQuestionsDao.getAll().toMutableList()
        val selectedEightyQuestions: MutableSet<PsdQuestionEntity> = mutableSetOf()

        while (selectedEightyQuestions.size < numberOfQuestions) {
            val index: Int = Random.nextInt(questions.size)
            selectedEightyQuestions.add(questions.removeAt(index))
        }

        return selectedEightyQuestions.toList().map { questionEntity ->
            Question(
                id=questionEntity.id,
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
