package com.kweku.armah.core.data.repository.fake

import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import com.kweku.armah.core.utilities.files.FileHelper
import javax.inject.Inject

class FakeQuestionsFileDataSourceImpl @Inject constructor(private val fileHelper: FileHelper) :
    QuestionsFileDataSource {

    override suspend fun getQuestionsFromFileIntoDatabase(resourceId: Int): List<Question> {
        return fileHelper.getQuestionsFromFile(resourceId)
    }
}
