package com.kweku.armah.core.utilities

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kweku.armah.core.utilities.files.FileHelper
import com.kweku.armah.core.utilities.files.FileHelperImpl
import com.kweku.armah.resources.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FileHelperTest {

    private lateinit var sut: FileHelper
    private lateinit var testContext: Context
    private lateinit var dispatcher: CoroutineDispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        testContext = InstrumentationRegistry.getInstrumentation().targetContext
        dispatcher = UnconfinedTestDispatcher()
        sut = FileHelperImpl(context = testContext, dispatcher = dispatcher)
    }

    @Test
    fun shouldReturnListOfQuestions() = runBlocking {
        val result = sut.getQuestionsFromFile(R.raw.psd)
        assert(result.isNotEmpty())
    }
}
