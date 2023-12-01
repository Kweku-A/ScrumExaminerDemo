package com.kweku.armah.core.data.repository

import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import com.kweku.armah.core.domain.repository.fakes.FakeQuestionsFileDataSource
import com.kweku.armah.core.utilities.files.FileHelper
import com.kweku.armah.core.utilities.files.fake.FakeFileHelperImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionsFileDataSourceTest {

    private lateinit var sut: QuestionsFileDataSource
    private lateinit var fileHelper: FileHelper

    @Before
    fun setup() {
        fileHelper = FakeFileHelperImpl()
        sut = QuestionsFileDataSourceImpl(fileHelper = fileHelper)
    }

    @Test
    fun shouldReturnListOfQuestionsFromFile() = runBlocking {
        val expected = FakeFileHelperImpl.fakeListOfQuestions
        val actual = sut.getQuestionsFromFileIntoDatabase(1)
        Assert.assertEquals(expected, actual)
    }
}
