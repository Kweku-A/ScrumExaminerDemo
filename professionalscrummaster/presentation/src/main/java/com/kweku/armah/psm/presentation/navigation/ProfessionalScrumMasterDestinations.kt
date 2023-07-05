package com.kweku.armah.psm.presentation.navigation

sealed class ProfessionalScrumMasterDestinations {
    data object IntroScreenDestination : ProfessionalScrumMasterDestinations()
    data object ReadyToStartScreenDestination : ProfessionalScrumMasterDestinations()
    data object QuizScreenDestination : ProfessionalScrumMasterDestinations()
    data object ResultScreenDestination : ProfessionalScrumMasterDestinations()
}
