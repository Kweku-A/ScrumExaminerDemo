package com.kweku.armah.psd.presentation.navigation

sealed class ProfessionalScrumDeveloperDestinations {
    data object IntroScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object ReadyToStartScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object QuizScreenDestination : ProfessionalScrumDeveloperDestinations()
    data object ResultScreenDestination : ProfessionalScrumDeveloperDestinations()
}
