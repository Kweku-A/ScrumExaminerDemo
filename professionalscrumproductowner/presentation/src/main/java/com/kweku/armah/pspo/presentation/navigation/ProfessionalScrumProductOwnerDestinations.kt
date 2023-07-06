package com.kweku.armah.pspo.presentation.navigation

sealed class ProfessionalScrumProductOwnerDestinations {
    data object PSPOIntroScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object PSPOReadyToStartScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object PSPOQuizScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object PSPOResultScreenDestination : ProfessionalScrumProductOwnerDestinations()
}
