package com.kweku.armah.testing.core.repository

import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import com.kweku.armah.core.domain.repository.fakes.FakeQuestionsFileDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionsFileDataSourceTest {

    private lateinit var sut: QuestionsFileDataSource

    @Before
    fun setup() {
        sut = FakeQuestionsFileDataSource()
    }

    @Test
    fun shouldReturnListOfQuestionsFromFile() = runBlocking {
        val expected = FakeQuestionsFileDataSource.fakeListOfQuestions
        val actual = sut.getQuestionsFromFileIntoDatabase(1)
        Assert.assertEquals(expected, actual)
    }
}
