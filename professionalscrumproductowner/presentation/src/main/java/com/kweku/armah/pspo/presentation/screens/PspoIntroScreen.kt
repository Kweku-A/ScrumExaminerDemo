package com.kweku.armah.pspo.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kweku.armah.core.presentation.composables.ExamIntroCard
import com.kweku.armah.pspo.presentation.viewmodels.PspoIntroViewModel
import com.kweku.armah.resources.R

@Composable
fun PspoIntroScreenRoute(
    navigateTo: () -> Unit = {},
    navigateBack: () -> Unit = {},
    viewModel: PspoIntroViewModel = hiltViewModel(),
) {
    PspoIntroScreen(navigateTo = navigateTo, navigateBack = navigateBack)
}

@Composable
fun PspoIntroScreen(navigateTo: () -> Unit, navigateBack: () -> Unit) {
    val modifier = Modifier.padding(vertical = 30.dp, horizontal = 15.dp)
    val introHeader = stringResource(
        R.string.welcome_to_the_exams,
        stringResource(R.string.professional_scrum_product_owner),
    )

    ExamIntroCard(
        introHeader = introHeader,
        navigateTo = navigateTo,
        navigateBack = navigateBack,
        modifier = modifier,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IntroScreenPreview() {
    PspoIntroScreen(navigateTo = {}, navigateBack = {})
}
