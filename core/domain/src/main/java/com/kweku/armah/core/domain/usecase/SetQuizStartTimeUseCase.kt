package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.QUIZ_TIME_IN_MINUTES
import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.datetime.Clock.System
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

class SetQuizStartTimeUseCase @Inject constructor(
    private val appPreferenceDataStore: AppPreferenceDataStore,
) {

    suspend operator fun invoke() {
        val timeNow = System.now().plus(QUIZ_TIME_IN_MINUTES.minutes)
        appPreferenceDataStore.updateDataStore(
            key = PreferenceKeys.quizEndTime,
            value = timeNow.toEpochMilliseconds(),
        )
    }
}
