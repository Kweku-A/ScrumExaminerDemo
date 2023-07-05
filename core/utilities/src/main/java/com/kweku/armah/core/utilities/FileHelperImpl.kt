package com.kweku.armah.core.utilities

import android.content.Context
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.stream.Collectors.toList
import javax.inject.Inject

class FileHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) :
    FileHelper {

    private fun readFromFile(resourceId: Int): List<String> {
        return context.resources.openRawResource(resourceId).use { inputStream ->
            inputStream.bufferedReader().use { bufferedReader ->
                bufferedReader.readLines()
            }
        }
    }

    override fun getQuestionsFromFile(resourceId: Int): List<Question> {
        val listOfQuestionEntity = mutableSetOf<Question>()
        val list = readFromFile(resourceId)
        var question = ""
        var answers = mutableListOf<Answer>()
        var correctAnswers = mutableListOf<Answer>()
        list.forEach {
            val line = it.trim()
            if (line.isNotEmpty()) {
                if (line.startsWith("###")) {
                    question = line.drop(3).trim()
                } else if (line.startsWith("- [x]")) {
                    answers.add(Answer(data = line.drop(5).trim()))
                    correctAnswers.add(Answer(data = line.drop(5).trim()))
                } else if (line.startsWith("- [ ]")) {
                    answers.add(Answer(data = line.drop(5).trim()))
                } else if (line.startsWith("**[")) {
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
        return listOfQuestionEntity.toList()
    }
}
