package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.model.PreferenceKeys
import com.kweku.armah.core.domain.model.QuizTimer
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import javax.inject.Inject

class GetCurrentQuizTimeLeftUseCase @Inject constructor(
    appPreferenceDataStore: AppPreferenceDataStore,
) : AppPreferenceDataStore by appPreferenceDataStore {

    operator fun invoke(): Flow<QuizTimer> = flow {
        val endTime =
            getDataStore(key = PreferenceKeys.quizEndTime).first() ?: 0L
        while (true) {
            val timeLeft = Clock.System.now()
                .periodUntil(
                    Instant.fromEpochMilliseconds(endTime),
                    TimeZone.currentSystemDefault(),
                )
            delay(1000)

            if (timeLeft.minutes <= 0 && timeLeft.seconds <= 0) {
                emit(
                    QuizTimer(
                        hours = 0,
                        minutes = 0,
                        seconds = 0,
                        isEnded = true,
                    ),
                )
                break
            } else {
                emit(
                    QuizTimer(
                        hours = timeLeft.hours,
                        minutes = timeLeft.minutes,
                        seconds = timeLeft.seconds,
                    ),
                )
            }
        }
    }
}
