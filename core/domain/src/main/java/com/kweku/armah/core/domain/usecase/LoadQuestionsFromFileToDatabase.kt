package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import javax.inject.Inject

class LoadQuestionsFromFileToDatabase @Inject constructor(repository: QuestionsFileDataSource) :
    QuestionsFileDataSource by repository {
    suspend operator fun invoke(resourceId: Int): List<Question> {
        return getQuestionsFromFileIntoDatabase(resourceId = resourceId)
    }
}
