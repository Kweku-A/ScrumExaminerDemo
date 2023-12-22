package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys.isQuizActive
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import javax.inject.Inject

class SetQuizOnOffUseCase @Inject constructor(appPreferenceDataStore: AppPreferenceDataStore) :
    AppPreferenceDataStore by appPreferenceDataStore {

    suspend operator fun invoke(isActive: Boolean = false) {
        updateDataStore(key = isQuizActive, value = isActive)
    }
}
