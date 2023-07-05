package com.kweku.armah.core.domain.repository

import com.kweku.armah.core.domain.model.Question

interface QuestionsFileDataSource {
    suspend fun getQuestionsFromFileIntoDatabase(resourceId: Int): List<Question>
}
