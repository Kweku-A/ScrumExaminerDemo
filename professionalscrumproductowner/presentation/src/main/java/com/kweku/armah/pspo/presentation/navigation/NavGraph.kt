package com.kweku.armah.pspo.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kweku.armah.pspo.presentation.screens.QuizScreenRoute
import com.kweku.armah.pspo.presentation.screens.ReadyToStartScreenRoute
import com.kweku.armah.pspo.presentation.screens.ResultScreenRoute
import com.kweku.armah.pspo.presentation.screens.PspoIntroScreenRoute

fun NavGraphBuilder.addPSPOIntroScreenRoute(navigateTo: () -> Unit, navigateBack: () -> Unit) {
    composable(route = ProfessionalScrumProductOwnerDestinations.IntroScreenDestination.toString()) {
        PspoIntroScreenRoute(navigateTo = navigateTo, navigateBack = navigateBack)
    }
}

fun NavGraphBuilder.addPSPOReadyToStartScreenRoute(navigateTo: () -> Unit) {
    composable(route = ProfessionalScrumProductOwnerDestinations.ReadyToStartScreenDestination.toString()) {
        ReadyToStartScreenRoute(navigateTo = navigateTo)
    }
}

const val REVIEW_PATH = "shouldReview"
fun NavGraphBuilder.addPSPOQuizScreenRoute(
    onFinishQuiz: () -> Unit,
    navigateToHome: () -> Unit = {},
) {
    composable(route = ProfessionalScrumProductOwnerDestinations.QuizScreenDestination.toString()) {
        QuizScreenRoute(onFinishQuiz = onFinishQuiz)
    }

    composable(
        route = "${ProfessionalScrumProductOwnerDestinations.QuizScreenDestination}/{$REVIEW_PATH}",
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

fun NavGraphBuilder.addPSPOResultsScreenRoute(
    navigateToHome: () -> Unit,
    navigateToReview: () -> Unit,
) {
    composable(route = ProfessionalScrumProductOwnerDestinations.ResultScreenDestination.toString()) {
        ResultScreenRoute(navigateToHome = navigateToHome, navigateToReview = navigateToReview)
    }
}
