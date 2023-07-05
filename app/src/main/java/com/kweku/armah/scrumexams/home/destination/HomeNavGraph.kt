package com.kweku.armah.scrumexams.home.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kweku.armah.scrumexams.home.HomeScreenRoute
import com.kweku.armah.scrumexams.home.enums.HomeButtons

fun NavGraphBuilder.addHomeScreenRoute(navigateTo: (HomeButtons) -> Unit, navigateToQuiz: () -> Unit) {
    composable(route = HomeDestinations.HomeScreenDestination.toString()) {
        HomeScreenRoute(navigateTo = navigateTo, navigateToQuiz = navigateToQuiz)
    }
}
