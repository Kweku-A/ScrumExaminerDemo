package com.kweku.armah.psd.presentation.screens

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.kweku.armah.resources.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalComposeUiApi::class)
class QuizScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var timeLeft = mutableStateOf("00:00")
    val setTimeLeft: (String) -> Unit = {
        timeLeft.value = it
    }

    private lateinit var context: Context

    @Before
    fun setup() {
        context = getInstrumentation().targetContext
        composeTestRule.setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.semantics { testTagsAsResourceId = true }) {
                    it.calculateBottomPadding()
                    PsdQuizScreen(
                        timeLeft = { timeLeft.value },
                        shouldReview = false,
                        listOfQuestions = listOf(),
                        setSelectedAnswers = { _, _ -> },
                        onFinishQuiz = {},
                        navigateToHome = {},

                    )
                }
            }
        }
    }

    @Test
    fun should_display_time_left() {
        val expectedTimeLeft = "00:50"
        setTimeLeft(expectedTimeLeft)
        with(composeTestRule) {
            onNodeWithText(context.getString(R.string.time_left, expectedTimeLeft)).assertExists()
        }
    }
}
