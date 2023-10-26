package com.kweku.armah.core.presentation.composables

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalComposeUiApi::class)
class ExamCardTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var clickContinueCount = 0
    private var clickBackCount = 0

    @Before
    fun setup() {
        val continueButtonClicked: () -> Unit = {
            clickContinueCount++
        }

        val backButtonClicked: () -> Unit = {
            clickBackCount++
        }
        composeTestRule.setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.semantics { testTagsAsResourceId = true }) {
                    it.calculateBottomPadding()
                    ExamIntroCard(
                        introHeader = "Header",
                        navigateTo = continueButtonClicked,
                        navigateBack = backButtonClicked,
                    )
                }
            }
        }
    }

    @Test
    fun navigate_forward_on_continue_clicked() {
        val device = UiDevice.getInstance(getInstrumentation())
        val continueButton: UiObject2 = device.findObject(By.res("continue_button"))
        continueButton.click()
        assert(clickContinueCount == 1)
        assert(clickBackCount == 0)
    }

    @Test
    fun navigate_back_on_back_clicked() {
        val device = UiDevice.getInstance(getInstrumentation())
        val continueButton: UiObject2 = device.findObject(By.res("back_to_home"))
        continueButton.click()
        assert(clickContinueCount == 0)
        assert(clickBackCount == 1)
    }
}