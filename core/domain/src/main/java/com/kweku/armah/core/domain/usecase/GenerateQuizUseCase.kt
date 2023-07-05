package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateQuizUseCase @Inject constructor(
    private val selectQuizQuestionsUseCase: SelectQuizQuestionsUseCase,
    private val saveSelectedQuizQuestionsUseCase: SaveSelectedQuizQuestionsUseCase,
) {

    operator fun invoke(
        questionsRepository: QuestionsRepository,
        quizQuestionsRepository: QuizQuestionsRepository,
    ) = flow {
        val questions = selectQuizQuestionsUseCase(questionsRepository = questionsRepository)
        saveSelectedQuizQuestionsUseCase(
            quizQuestionsRepository = quizQuestionsRepository,
            questions = questions,
        )
        emit(true)
    }
}
