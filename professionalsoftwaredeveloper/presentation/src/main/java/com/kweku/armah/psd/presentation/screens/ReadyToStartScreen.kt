package com.kweku.armah.psd.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kweku.armah.core.presentation.composables.ReadyToStartBody
import com.kweku.armah.psd.presentation.viewmodels.ReadyToStartViewModel

@Composable
fun ReadyToStartScreenRoute(
    navigateTo: () -> Unit = {},
    viewModel: ReadyToStartViewModel = hiltViewModel(),
) {
    val generateQuiz: () -> Unit = {
        viewModel.generateQuiz()
        viewModel.startQuizTimer()
    }

    val isQuizReady by viewModel.isQuizReadyStateFlow.collectAsStateWithLifecycle()
    if (isQuizReady) {
        navigateTo()
    }
    ReadyToStartScreen(generateQuiz = generateQuiz)
}

@Composable
private fun ReadyToStartScreen(generateQuiz: () -> Unit) {
    val modifier = Modifier.padding(vertical = 30.dp, horizontal = 15.dp)
    ReadyToStartBody(modifier, generateQuiz)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IntroScreenPreview() {
    ReadyToStartScreen({})
}
