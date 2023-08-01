package com.kweku.armah.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.platform.app.InstrumentationRegistry
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppPreferenceDataStoreTest {
    private lateinit var sut: AppPreferenceDataStore

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        sut = AppPreferenceDataStoreImpl(context = context)
    }

    @Test
    fun set_and_return_boolean() = runBlocking {
        val expected = true
        sut.updateDataStore(key = booleanPreferencesKey(name = "booleanValue"), expected)
        val actual = sut.getDataStore(key = booleanPreferencesKey(name = "booleanValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun set_and_return_string() = runBlocking {
        val expected = "aaa"
        sut.updateDataStore(key = stringPreferencesKey(name = "stringValue"), expected)
        val actual = sut.getDataStore(key = stringPreferencesKey(name = "stringValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun set_and_return_integer() = runBlocking {
        val expected = 12
        sut.updateDataStore(key = intPreferencesKey(name = "intValue"), expected)
        val actual = sut.getDataStore(key = intPreferencesKey(name = "intValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun set_and_return_double() = runBlocking {
        val expected = 12.0
        sut.updateDataStore(key = doublePreferencesKey(name = "doubleValue"), expected)
        val actual = sut.getDataStore(key = doublePreferencesKey(name = "doubleValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun set_and_return_float() = runBlocking {
        val expected = 12f
        sut.updateDataStore(key = floatPreferencesKey(name = "floatValue"), expected)
        val actual = sut.getDataStore(key = floatPreferencesKey(name = "floatValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun set_and_return_long() = runBlocking {
        val expected = 12L
        sut.updateDataStore(key = longPreferencesKey(name = "longValue"), expected)
        val actual = sut.getDataStore(key = longPreferencesKey(name = "longValue")).first()
        Assert.assertEquals(expected, actual)
    }

    @After
    fun tearDown() = runBlocking {
        sut.clear()
    }
}
