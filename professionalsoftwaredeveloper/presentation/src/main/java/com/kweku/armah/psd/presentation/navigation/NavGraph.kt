package com.kweku.armah.psd.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kweku.armah.psd.presentation.screens.PsdIntroScreenRoute
import com.kweku.armah.psd.presentation.screens.QuizScreenRoute
import com.kweku.armah.psd.presentation.screens.ReadyToStartScreenRoute
import com.kweku.armah.psd.presentation.screens.ResultScreenRoute

fun NavGraphBuilder.addPSDIntroScreenRoute(navigateTo: () -> Unit, navigateBack: () -> Unit) {
    composable(route = ProfessionalScrumDeveloperDestinations.IntroScreenDestination.toString()) {
        PsdIntroScreenRoute(navigateTo = navigateTo, navigateBack = navigateBack)
    }
}

fun NavGraphBuilder.addPSDReadyToStartScreenRoute(navigateTo: () -> Unit) {
    composable(route = ProfessionalScrumDeveloperDestinations.ReadyToStartScreenDestination.toString()) {
        ReadyToStartScreenRoute(navigateTo = navigateTo)
    }
}

const val REVIEW_PATH = "shouldReview"
fun NavGraphBuilder.addPSDQuizScreenRoute(
    onFinishQuiz: () -> Unit,
    navigateToHome: () -> Unit = {},
) {
    composable(route = ProfessionalScrumDeveloperDestinations.QuizScreenDestination.toString()) {
        QuizScreenRoute(onFinishQuiz = onFinishQuiz)
    }

    composable(
        route = "${ProfessionalScrumDeveloperDestinations.QuizScreenDestination}/{$REVIEW_PATH}",
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

fun NavGraphBuilder.addPSDResultsScreenRoute(
    navigateToHome: () -> Unit,
    navigateToReview: () -> Unit,
) {
    composable(route = ProfessionalScrumDeveloperDestinations.ResultScreenDestination.toString()) {
        ResultScreenRoute(navigateToHome = navigateToHome, navigateToReview = navigateToReview)
    }
}
