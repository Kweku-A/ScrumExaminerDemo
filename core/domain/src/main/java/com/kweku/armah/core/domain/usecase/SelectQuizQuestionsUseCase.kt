package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.NUMBER_OF_QUIZ_QUESTIONS
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository
import javax.inject.Inject

class SelectQuizQuestionsUseCase @Inject constructor() {

    suspend operator fun invoke(questionsRepository: QuestionsRepository): List<Question> {
        return questionsRepository.selectRandomQuestions(numberOfQuestions = NUMBER_OF_QUIZ_QUESTIONS)
    }
}
