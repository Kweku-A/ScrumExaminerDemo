package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import javax.inject.Inject

class SetActiveQuizRouteUseCase @Inject constructor(private val appPreferenceDataStore: AppPreferenceDataStore) {

    suspend operator fun invoke(activeQuizRoute: String) {
        appPreferenceDataStore.updateDataStore(
            key = PreferenceKeys.activeQuizRoute,
            value = activeQuizRoute,
        )
    }
}
