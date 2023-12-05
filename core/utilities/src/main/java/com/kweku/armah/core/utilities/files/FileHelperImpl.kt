package com.kweku.armah.core.utilities.files

import android.content.Context
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FileHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) :
    FileHelper {

    private suspend fun readFromFile(resourceId: Int): List<String> {
        return withContext(dispatcher) {
            context.resources.openRawResource(resourceId).use { inputStream ->
                inputStream.bufferedReader().use { bufferedReader ->
                    bufferedReader.readLines()
                }
            }
        }
    }

    override suspend fun getQuestionsFromFile(resourceId: Int): List<Question> {
        val listOfQuestionEntity = mutableSetOf<Question>()
        val list = readFromFile(resourceId)
        var question = ""
        var answers = mutableListOf<Answer>()
        var correctAnswers = mutableListOf<Answer>()
        list.forEach {
            val line = it.trim()
            if (line.isNotEmpty()) {
                when {
                    line.startsWith("###") -> {
                        question = line.drop(3).trim()
                    }
                    line.startsWith("- [x]") -> {
                        answers.add(Answer(data = line.drop(5).trim()))
                        correctAnswers.add(Answer(data = line.drop(5).trim()))
                    }
                    line.startsWith("- [ ]") -> {
                        answers.add(Answer(data = line.drop(5).trim()))
                    }
                    line.startsWith("**[") -> {
                        listOfQuestionEntity.add(
                            Question(
                                question = question,
                                answers = answers,
                                correctAnswers = correctAnswers,
                                selectedAnswers = mutableListOf(),
                            ),
                        )
                        question = ""
                        answers = mutableListOf<Answer>()
                        correctAnswers = mutableListOf<Answer>()
                    }
                }
            }
        }
        return listOfQuestionEntity.toList()
    }
}
