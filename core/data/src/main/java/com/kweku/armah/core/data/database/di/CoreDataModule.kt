package com.kweku.armah.core.data.database.di

import android.content.Context
import com.kweku.armah.core.data.datastore.AppPreferenceDataStoreImpl
import com.kweku.armah.core.data.repository.QuestionsFileDataSourceImpl
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.repository.AppPreferenceDataStore
import com.kweku.armah.core.domain.repository.QuestionsFileDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreDataModule {

    @Singleton
    @Provides
    fun provideQuestionsRepository(questionsRepositoryImpl: QuestionsFileDataSourceImpl): QuestionsFileDataSource {
        return questionsRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideAppPref(
        @ApplicationContext context: Context,
        @IODispatcher dispatcher: CoroutineDispatcher,
    ): AppPreferenceDataStore {
        return AppPreferenceDataStoreImpl(context, dispatcher)
    }
}
