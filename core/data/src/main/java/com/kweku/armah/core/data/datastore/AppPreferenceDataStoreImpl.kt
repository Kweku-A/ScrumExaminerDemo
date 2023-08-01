package com.kweku.armah.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quiz_prefs")

class AppPreferenceDataStoreImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AppPreferenceDataStore {

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
