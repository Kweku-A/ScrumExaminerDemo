package com.kweku.armah.psd.data.di

import com.google.gson.Gson
import com.kweku.armah.core.utilities.FileHelper
import com.kweku.armah.core.utilities.FileHelperImpl
import com.kweku.armah.psd.data.repository.PsdQuestionsRepositoryImpl
import com.kweku.armah.psd.data.repository.PsdQuizQuestionsRepositoryImpl
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.psd.domain.ProfessionalScrumDeveloper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideFileHelper(fileHelperImpl: FileHelperImpl): FileHelper {
        return fileHelperImpl
    }

    @Singleton
    @Provides
    @ProfessionalScrumDeveloper
    fun provideQuizRepository(repositoryImpl: PsdQuizQuestionsRepositoryImpl): QuizQuestionsRepository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    @ProfessionalScrumDeveloper
    fun provideRepository(repositoryImpl: PsdQuestionsRepositoryImpl): QuestionsRepository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
