package com.kweku.armah.psm.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kweku.armah.core.presentation.composables.QuizScreenBody
import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi
import com.kweku.armah.psm.presentation.viewmodels.PsmQuizScreenViewModel

@Composable
fun QuizScreenRoute(
    onFinishQuiz: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    viewModel: PsmQuizScreenViewModel = hiltViewModel(),
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

    PsmQuizScreen(
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
fun PsmQuizScreen(
    timeLeft: () -> String,
    shouldReview: Boolean,
    listOfQuestions: List<QuestionsUi>,
    setSelectedAnswers: (List<AnswerUi>, Int) -> Unit,
    onFinishQuiz: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val modifier = Modifier.padding(vertical = 30.dp, horizontal = 0.dp)
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        // provide pageCount
        listOfQuestions.size
    }
    val coroutineScope = rememberCoroutineScope()

    val selectedAnswers: (List<AnswerUi>) -> Unit = {
        setSelectedAnswers(it, pagerState.currentPage)
    }

    QuizScreenBody(
        modifier = modifier,
        pagerState = pagerState,
        questionsCount = listOfQuestions.size,
        shouldReview = shouldReview,
        timeLeft = timeLeft,
        listOfQuestions = listOfQuestions,
        selectedAnswers = selectedAnswers,
        navigateToHome = navigateToHome,
        onFinishQuiz = onFinishQuiz,
        coroutineScope = coroutineScope,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PsmQuizScreenPreview() {
    PsmQuizScreen(
        { "00:00:00" },
        true,
        listOfQuestions = emptyList(),
        setSelectedAnswers = { _, _ -> },
        {},
    ) {}
}
