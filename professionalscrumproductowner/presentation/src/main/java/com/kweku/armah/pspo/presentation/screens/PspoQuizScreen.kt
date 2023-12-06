package com.kweku.armah.pspo.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kweku.armah.core.presentation.composables.QuizScreenBody
import com.kweku.armah.core.presentation.composables.QuizScreenData
import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi
import com.kweku.armah.pspo.presentation.viewmodels.PspoQuizScreenViewModel

@Composable
fun QuizScreenRoute(
    onFinishQuiz: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    viewModel: PspoQuizScreenViewModel = hiltViewModel(),
    shouldReview: Boolean = false,
) {
    val listOfQuestions by viewModel.quizQuestionsStateFlow.collectAsStateWithLifecycle()
    val setSelectedAnswers: (List<AnswerUi>, Int) -> Unit = { list, index ->
        viewModel.setAnswers(list, index)
    }

    viewModel.setQuizStateOnOff(shouldReview)
    val timeLeft by viewModel.timeLeftStateFlow.collectAsStateWithLifecycle()

    val timerFinished by viewModel.timeFinished.collectAsStateWithLifecycle()

    if (timerFinished && !shouldReview) {
        onFinishQuiz()
    }

    PspoQuizScreen(
        timeLeft = { timeLeft },
        shouldReview = shouldReview,
        listOfQuestions = listOfQuestions,
        setSelectedAnswers = setSelectedAnswers,
        onFinishQuiz = onFinishQuiz,
        navigateToHome = navigateToHome,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PspoQuizScreen(
    timeLeft: () -> String,
    shouldReview: Boolean,
    listOfQuestions: List<QuestionsUi>,
    setSelectedAnswers: (List<AnswerUi>, Int) -> Unit,
    onFinishQuiz: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val modifier = Modifier.padding(vertical = 30.dp, horizontal = 0.dp)
    val pagerState = rememberPagerState() { listOfQuestions.size }

    val selectedAnswers: (List<AnswerUi>) -> Unit = {
        setSelectedAnswers(it, pagerState.currentPage)
    }

    QuizScreenBody(
        modifier = modifier,
        pagerState = pagerState,
        quizScreenData = QuizScreenData(
            questionsCount = listOfQuestions.size,
            shouldReview = shouldReview,
            timeLeft = timeLeft,
            listOfQuestions = listOfQuestions,
        ),
        selectedAnswers = selectedAnswers,
        navigateToHome = navigateToHome,
        onFinishQuiz = onFinishQuiz,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PspoQuizScreenPreview() {
    PspoQuizScreen(
        { "00:00:00" },
        true,
        listOfQuestions = emptyList(),
        setSelectedAnswers = { _, _ -> },
        {},
    ) {}
}
