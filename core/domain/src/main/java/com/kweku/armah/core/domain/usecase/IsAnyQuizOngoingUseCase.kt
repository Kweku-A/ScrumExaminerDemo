package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsAnyQuizOngoingUseCase @Inject constructor(
    private val appPreferenceDataStore: AppPreferenceDataStore,
) {

    operator fun invoke(): Flow<Boolean> =
        appPreferenceDataStore.getDataStore(key = PreferenceKeys.isQuizActive).map {
            it ?: false
        }
}
