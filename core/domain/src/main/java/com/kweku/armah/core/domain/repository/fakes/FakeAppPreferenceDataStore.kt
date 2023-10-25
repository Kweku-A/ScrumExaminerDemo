package com.kweku.armah.core.domain.repository.fakes

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "test_quiz_prefs")
class FakeAppPreferenceDataStore(private val context: Context) : AppPreferenceDataStore {
    override suspend fun <T> updateDataStore(key: Preferences.Key<T>, value: T): Preferences =
        context.dataStore.edit { prefs ->
            when (value) {
                is String -> {
                    prefs[key] = value
                }

                is Long -> {
                    prefs[key] = value
                }

                is Boolean -> {
                    prefs[key] = value
                }

                is Double -> {
                    prefs[key] = value
                }

                is Int -> {
                    prefs[key] = value
                }

                is Float -> {
                    prefs[key] = value
                }

                else -> {
                    throw UnsupportedOperationException("The type you passed is not supported")
                }
            }
        }

    override fun <T> getDataStore(key: Preferences.Key<T>): Flow<T?> =
        context.dataStore.data.map {
            it[key]
        }

    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
