package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsRepository
import javax.inject.Inject

class InsertAllQuestionsIntoDatabaseUseCase @Inject constructor() {

    suspend operator fun invoke(
        questionsRepository: QuestionsRepository,
        questionsGroup: List<Question>,
    ) {
        questionsRepository.insertQuestionsIntoData(questionGroup = questionsGroup)
    }
}
