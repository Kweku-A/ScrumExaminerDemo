package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import javax.inject.Inject

class SetActiveQuizRouteUseCase @Inject constructor(appPreferenceDataStore: AppPreferenceDataStore) :
    AppPreferenceDataStore by appPreferenceDataStore {

    suspend operator fun invoke(activeQuizRoute: String) {
        updateDataStore(
            key = PreferenceKeys.activeQuizRoute,
            value = activeQuizRoute,
        )
    }
}
