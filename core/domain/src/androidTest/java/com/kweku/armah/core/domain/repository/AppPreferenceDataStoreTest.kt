package com.kweku.armah.core.domain.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kweku.armah.core.domain.repository.fakes.FakeAppPreferenceDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class AppPreferenceDataStoreTest {

    private lateinit var sut: AppPreferenceDataStore
    private lateinit var testContext: Context

    @Before
    fun setup() {
        testContext = InstrumentationRegistry.getInstrumentation().targetContext
        sut = FakeAppPreferenceDataStore(testContext)
    }

    @Test
    fun shouldSaveString() = runBlocking {
        val testStringPref: Preferences.Key<String> = stringPreferencesKey(name = "testStringPref")
        val expected = "test"

        sut.updateDataStore(
            key = testStringPref,
            value = expected,
        )

        val actual = sut.getDataStore(testStringPref).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun shouldSaveLong() = runBlocking {
        val testLongPref: Preferences.Key<Long> = longPreferencesKey(name = "testLongPref")
        val expected = 7L

        sut.updateDataStore(
            key = testLongPref,
            value = expected,
        )

        val actual = sut.getDataStore(testLongPref).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun shouldSaveDouble() = runBlocking {
        val testDoublePref: Preferences.Key<Double> = doublePreferencesKey(name = "testDoublePref")
        val expected = 0.7

        sut.updateDataStore(
            key = testDoublePref,
            value = expected,
        )

        val actual = sut.getDataStore(testDoublePref).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun shouldSaveBoolean() = runBlocking {
        val testBooleanPref: Preferences.Key<Double> =
            doublePreferencesKey(name = "testBooleanPref")
        val expected = 0.7

        sut.updateDataStore(
            key = testBooleanPref,
            value = expected,
        )

        val actual = sut.getDataStore(testBooleanPref).first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun shouldSaveFloat() = runBlocking {
        val testFloatPref: Preferences.Key<Float> = floatPreferencesKey(name = "testFloatPref")
        val expected = 0.7f

        sut.updateDataStore(
            key = testFloatPref,
            value = expected,
        )

        val actual = sut.getDataStore(testFloatPref).first()
        Assert.assertEquals(expected, actual)
    }

    @After
    fun tearDown() = runBlocking {
        sut.clear()
    }
}
