package com.kweku.armah.pspo.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kweku.armah.core.presentation.composables.ResultScreenBody
import com.kweku.armah.core.presentation.data.FinalScoreUi
import com.kweku.armah.pspo.presentation.viewmodels.PspoResultScreenViewModel

@Composable
fun ResultScreenRoute(
    navigateToReview: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    viewModel: PspoResultScreenViewModel = hiltViewModel(),
) {
    val finalScoreUi by viewModel.finalScoreStateFlow.collectAsStateWithLifecycle()

    val clearFinishedQuiz: () -> Unit = {
        viewModel.clearQuiz()
    }

    val resetQuizFlag: () -> Unit = {
        viewModel.resetOnGoingQuizFlag()
    }

    PspoResultScreen(
        finalScore = finalScoreUi,
        navigateToReview = navigateToReview,
        clearFinishedQuiz = clearFinishedQuiz,
        navigateToHome = navigateToHome,
        resetQuizFlag = resetQuizFlag,
    )
}

@Composable
fun PspoResultScreen(
    finalScore: FinalScoreUi,
    navigateToReview: () -> Unit = {},
    clearFinishedQuiz: () -> Unit = {},
    resetQuizFlag: () -> Unit = {},
    navigateToHome: () -> Unit = {},
) {
    val modifier = Modifier.padding(vertical = 30.dp, horizontal = 15.dp)
    ResultScreenBody(
        modifier,
        finalScore,
        resetQuizFlag,
        navigateToReview,
        clearFinishedQuiz,
        navigateToHome,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PspoResultScreenPreview() {
    PspoResultScreen(FinalScoreUi())
}
