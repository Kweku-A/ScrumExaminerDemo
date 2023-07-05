package com.kweku.armah.core.data.repository

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import com.kweku.armah.core.utilities.files.FileHelper
import javax.inject.Inject

class QuestionsFileDataSourceImpl @Inject constructor(
    private val fileHelper: FileHelper,
) : QuestionsFileDataSource {

    override suspend fun getQuestionsFromFileIntoDatabase(resourceId: Int): List<Question> {
        return fileHelper.getQuestionsFromFile(resourceId = resourceId)
    }
}
