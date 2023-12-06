package com.kweku.armah.core.domain.model

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val isQuizActive: Preferences.Key<Boolean> = booleanPreferencesKey(name = "isQuizActive")
    val activeQuizRoute: Preferences.Key<String> = stringPreferencesKey(name = "activeQuizRoute")
    val quizEndTime: Preferences.Key<Long> = longPreferencesKey(name = "quizStartTime")
}
