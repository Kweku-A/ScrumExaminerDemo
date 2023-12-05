package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import javax.inject.Inject

class GetQuizUseCase @Inject constructor() {

    suspend operator fun invoke(quizQuestionsRepository: QuizQuestionsRepository): List<Question> {
        return quizQuestionsRepository.getQuiz()
    }
}
