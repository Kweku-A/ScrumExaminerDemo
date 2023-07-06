package com.kweku.armah.psm.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kweku.armah.psm.presentation.screens.PsdIntroScreenRoute
import com.kweku.armah.psm.presentation.screens.QuizScreenRoute
import com.kweku.armah.psm.presentation.screens.ReadyToStartScreenRoute
import com.kweku.armah.psm.presentation.screens.ResultScreenRoute

fun NavGraphBuilder.addPSMIntroScreenRoute(navigateTo: () -> Unit, navigateBack: () -> Unit) {
    composable(route = ProfessionalScrumMasterDestinations.PSMIntroScreenDestination.toString()) {
        PsdIntroScreenRoute(navigateTo = navigateTo, navigateBack = navigateBack)
    }
}

fun NavGraphBuilder.addPSMReadyToStartScreenRoute(navigateTo: () -> Unit) {
    composable(route = ProfessionalScrumMasterDestinations.PSMReadyToStartScreenDestination.toString()) {
        ReadyToStartScreenRoute(navigateTo = navigateTo)
    }
}

const val REVIEW_PATH = "shouldReview"
fun NavGraphBuilder.addPSMQuizScreenRoute(
    onFinishQuiz: () -> Unit,
    navigateToHome: () -> Unit = {},
) {
    composable(route = ProfessionalScrumMasterDestinations.PSMQuizScreenDestination.toString()) {
        QuizScreenRoute(onFinishQuiz = onFinishQuiz)
    }

    composable(
        route = "${ProfessionalScrumMasterDestinations.PSMQuizScreenDestination}/{$REVIEW_PATH}",
        arguments = listOf(
            navArgument(name = REVIEW_PATH) {
                defaultValue = false
                type = NavType.BoolType
            },
        ),
    ) {
        val shouldReview = it.arguments?.getBoolean(REVIEW_PATH) ?: false
        QuizScreenRoute(
            shouldReview = shouldReview,
            onFinishQuiz = onFinishQuiz,
            navigateToHome = navigateToHome,
        )
    }
}

fun NavGraphBuilder.addPSMResultsScreenRoute(
    navigateToHome: () -> Unit,
    navigateToReview: () -> Unit,
) {
    composable(route = ProfessionalScrumMasterDestinations.PSMResultScreenDestination.toString()) {
        ResultScreenRoute(navigateToHome = navigateToHome, navigateToReview = navigateToReview)
    }
}
