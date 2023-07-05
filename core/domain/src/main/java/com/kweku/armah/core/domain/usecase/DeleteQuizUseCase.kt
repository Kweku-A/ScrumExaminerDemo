package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import javax.inject.Inject

class DeleteQuizUseCase @Inject constructor() {

    suspend operator fun invoke(quizQuestionsRepository: QuizQuestionsRepository) {
        return quizQuestionsRepository.deleteAllQuiz()
    }
}
