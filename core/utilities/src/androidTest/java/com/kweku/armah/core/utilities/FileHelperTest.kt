package com.kweku.armah.core.utilities

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kweku.armah.core.utilities.files.FileHelper
import com.kweku.armah.core.utilities.files.FileHelperImpl
import com.kweku.armah.resources.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FileHelperTest {

    private lateinit var sut: FileHelper
    private lateinit var testContext: Context

    @Before
    fun setup() {
        testContext = InstrumentationRegistry.getInstrumentation().targetContext
        sut = FileHelperImpl(testContext)
    }

    @Test
    fun shouldReturnListOfQuestions() {
        val result = sut.getQuestionsFromFile(R.raw.psd)
        assert(result.isNotEmpty())
    }
}
