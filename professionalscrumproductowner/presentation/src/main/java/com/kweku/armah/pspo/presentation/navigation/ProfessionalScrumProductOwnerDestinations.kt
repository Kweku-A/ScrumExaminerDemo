package com.kweku.armah.pspo.presentation.navigation

sealed class ProfessionalScrumProductOwnerDestinations {
    data object IntroScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object ReadyToStartScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object QuizScreenDestination : ProfessionalScrumProductOwnerDestinations()
    data object ResultScreenDestination : ProfessionalScrumProductOwnerDestinations()
}
