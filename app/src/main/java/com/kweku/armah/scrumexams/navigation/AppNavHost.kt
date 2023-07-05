package com.kweku.armah.scrumexams.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kweku.armah.psd.presentation.navigation.ProfessionalScrumDeveloperDestinations
import com.kweku.armah.psd.presentation.navigation.addPSDIntroScreenRoute
import com.kweku.armah.psd.presentation.navigation.addPSDQuizScreenRoute
import com.kweku.armah.psd.presentation.navigation.addPSDReadyToStartScreenRoute
import com.kweku.armah.psd.presentation.navigation.addPSDResultsScreenRoute
import com.kweku.armah.psm.presentation.navigation.ProfessionalScrumMasterDestinations
import com.kweku.armah.psm.presentation.navigation.addPSMIntroScreenRoute
import com.kweku.armah.psm.presentation.navigation.addPSMQuizScreenRoute
import com.kweku.armah.psm.presentation.navigation.addPSMReadyToStartScreenRoute
import com.kweku.armah.psm.presentation.navigation.addPSMResultsScreenRoute
import com.kweku.armah.pspo.presentation.navigation.ProfessionalScrumProductOwnerDestinations
import com.kweku.armah.pspo.presentation.navigation.addPSPOIntroScreenRoute
import com.kweku.armah.pspo.presentation.navigation.addPSPOQuizScreenRoute
import com.kweku.armah.pspo.presentation.navigation.addPSPOReadyToStartScreenRoute
import com.kweku.armah.pspo.presentation.navigation.addPSPOResultsScreenRoute
import com.kweku.armah.scrumexams.home.destination.HomeDestinations
import com.kweku.armah.scrumexams.home.destination.addHomeScreenRoute
import com.kweku.armah.scrumexams.home.enums.HomeButtons

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = HomeDestinations.HomeScreenDestination.toString()

    val navigateTo: (String) -> Unit = { route ->
        navController.navigate(route)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        addHomeScreenRoute(navigateTo = {
            val destination = when (it) {
                is HomeButtons.PSM -> {
                    ProfessionalScrumMasterDestinations.IntroScreenDestination.toString()
                }

                is HomeButtons.PSD -> {
                    ProfessionalScrumDeveloperDestinations.IntroScreenDestination.toString()
                }

                is HomeButtons.PSPO -> {
                    ProfessionalScrumProductOwnerDestinations.IntroScreenDestination.toString()
                }
            }
            navController.navigate(destination)
        }, navigateToQuiz = {
            navController.navigate(ProfessionalScrumDeveloperDestinations.QuizScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSDIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumDeveloperDestinations.ReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSDReadyToStartScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumDeveloperDestinations.QuizScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSDQuizScreenRoute(onFinishQuiz = {
            navController.navigate(ProfessionalScrumDeveloperDestinations.ResultScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSDResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumDeveloperDestinations.QuizScreenDestination}/true")
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        // PSM

        addPSMIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumMasterDestinations.ReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSMReadyToStartScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumMasterDestinations.QuizScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSMQuizScreenRoute(onFinishQuiz = {
            navController.navigate(ProfessionalScrumMasterDestinations.ResultScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSMResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumMasterDestinations.QuizScreenDestination}/true")
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        // PSPO

        addPSPOIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumProductOwnerDestinations.ReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSPOReadyToStartScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumProductOwnerDestinations.QuizScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSPOQuizScreenRoute(onFinishQuiz = {
            navController.navigate(ProfessionalScrumProductOwnerDestinations.ResultScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })

        addPSPOResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumProductOwnerDestinations.QuizScreenDestination}/true")
        }, navigateToHome = {
            navController.navigate(HomeDestinations.HomeScreenDestination.toString()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        })
    }
}
