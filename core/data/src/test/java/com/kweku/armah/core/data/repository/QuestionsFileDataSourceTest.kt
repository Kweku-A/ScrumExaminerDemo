package com.kweku.armah.core.data.repository

import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import com.kweku.armah.core.utilities.files.FileHelper
import com.kweku.armah.core.utilities.files.fake.FakeFileHelperImpl
import com.kweku.armah.core.utilities.files.fake.fakeListOfQuestions
import kotlinx.coroutines.test.runTest
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
    fun should_return_list_of_questions() = runTest {
        val expected = fakeListOfQuestions
        val actual = sut.getQuestionsFromFileIntoDatabase(0)
        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected)) // O^2
    }
}
