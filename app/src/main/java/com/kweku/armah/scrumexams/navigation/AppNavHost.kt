package com.kweku.armah.scrumexams.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val navHostViewModel: NavHostViewModel = hiltViewModel()
    val startDestination = HomeDestinations.HomeScreenDestination.toString()

    val navigateToDestinationAndClearStack: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    val activeRoute by navHostViewModel.activeQuizRoute.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        addHomeScreenRoute(
            navigateTo = {
                val destination = when (it) {
                    is HomeButtons.PSM -> {
                        ProfessionalScrumMasterDestinations.PSMIntroScreenDestination.toString()
                    }

                    is HomeButtons.PSD -> {
                        ProfessionalScrumDeveloperDestinations.PSDIntroScreenDestination.toString()
                    }

                    is HomeButtons.PSPO -> {
                        ProfessionalScrumProductOwnerDestinations.PSPOIntroScreenDestination.toString()
                    }
                }
                navController.navigate(destination)
            },
            navigateToActiveQuiz = {
                if (activeRoute.isNotEmpty()) {
                    navigateToDestinationAndClearStack(activeRoute)
                }
            },
        )

        addPSDIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumDeveloperDestinations.PSDReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSDReadyToStartScreenRoute(navigateTo = {
            navHostViewModel.setActiveQuizState(
                isActive = true,
                route = ProfessionalScrumDeveloperDestinations.PSDQuizScreenDestination.toString(),
            )
            navigateToDestinationAndClearStack(ProfessionalScrumDeveloperDestinations.PSDQuizScreenDestination.toString())
        })

        addPSDQuizScreenRoute(onFinishQuiz = {
            navHostViewModel.setActiveQuizState(isActive = false)
            navigateToDestinationAndClearStack(ProfessionalScrumDeveloperDestinations.PSDResultScreenDestination.toString())
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })

        addPSDResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumDeveloperDestinations.PSDQuizScreenDestination}/true")
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })

        // PSM

        addPSMIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumMasterDestinations.PSMReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSMReadyToStartScreenRoute(navigateTo = {
            navHostViewModel.setActiveQuizState(
                isActive = true,
                route = ProfessionalScrumMasterDestinations.PSMQuizScreenDestination.toString(),
            )
            navigateToDestinationAndClearStack(ProfessionalScrumMasterDestinations.PSMQuizScreenDestination.toString())
        })

        addPSMQuizScreenRoute(onFinishQuiz = {
            navHostViewModel.setActiveQuizState(false)
            navigateToDestinationAndClearStack(ProfessionalScrumMasterDestinations.PSMResultScreenDestination.toString())
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })

        addPSMResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumMasterDestinations.PSMQuizScreenDestination}/true")
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })

        // PSPO

        addPSPOIntroScreenRoute(navigateTo = {
            navController.navigate(ProfessionalScrumProductOwnerDestinations.PSPOReadyToStartScreenDestination.toString())
        }, navigateBack = {
            navController.navigateUp()
        })

        addPSPOReadyToStartScreenRoute(navigateTo = {
            navHostViewModel.setActiveQuizState(
                isActive = true,
                route = ProfessionalScrumProductOwnerDestinations.PSPOQuizScreenDestination.toString(),
            )
            navigateToDestinationAndClearStack(ProfessionalScrumProductOwnerDestinations.PSPOQuizScreenDestination.toString())
        })

        addPSPOQuizScreenRoute(onFinishQuiz = {
            navHostViewModel.setActiveQuizState(false)
            navigateToDestinationAndClearStack(ProfessionalScrumProductOwnerDestinations.PSPOResultScreenDestination.toString())
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })

        addPSPOResultsScreenRoute(navigateToReview = {
            // navigate back to questions for review
            navController.navigate("${ProfessionalScrumProductOwnerDestinations.PSPOQuizScreenDestination}/true")
        }, navigateToHome = {
            navigateToDestinationAndClearStack(startDestination)
        })
    }
}
