package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import javax.inject.Inject

class LoadQuestionsFromFileToDatabase @Inject constructor(private val repository: QuestionsFileDataSource) {
    suspend operator fun invoke(resourceId: Int): List<Question> {
        return repository.getQuestionsFromFileIntoDatabase(resourceId = resourceId)
    }
}
