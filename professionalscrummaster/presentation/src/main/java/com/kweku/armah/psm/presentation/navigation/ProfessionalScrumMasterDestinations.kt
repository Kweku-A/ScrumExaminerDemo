package com.kweku.armah.psm.presentation.navigation

sealed class ProfessionalScrumMasterDestinations {
    data object PSMIntroScreenDestination : ProfessionalScrumMasterDestinations()
    data object PSMReadyToStartScreenDestination : ProfessionalScrumMasterDestinations()
    data object PSMQuizScreenDestination : ProfessionalScrumMasterDestinations()
    data object PSMResultScreenDestination : ProfessionalScrumMasterDestinations()
}
