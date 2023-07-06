package com.kweku.armah.psd.presentation.navigation

sealed class ProfessionalScrumDeveloperDestinations {
    data object PSDIntroScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object PSDReadyToStartScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object PSDQuizScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object PSDResultScreenDestination : ProfessionalScrumDeveloperDestinations()
}
