package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import javax.inject.Inject

class SaveSelectedQuizQuestionsUseCase @Inject constructor() {

    suspend operator fun invoke(quizQuestionsRepository: QuizQuestionsRepository, questions: List<Question>) {
        quizQuestionsRepository.saveSelectedQuizQuestionsToDatabase(quizQuestions = questions)
    }
}
