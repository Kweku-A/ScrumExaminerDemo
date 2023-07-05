package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsAnyQuizOngoingUseCase @Inject constructor(
    private val appPreferenceDataStore: AppPreferenceDataStore,
) {

    operator fun invoke(): Flow<Boolean> = flow {
        val isOnGoing =
            appPreferenceDataStore.getDataStore(key = PreferenceKeys.isQuizActive).first() ?: false
        emit(isOnGoing)
    }
}
