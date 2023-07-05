package com.kweku.armah.psm.data.di

import com.google.gson.Gson
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.psm.data.repository.PsmQuestionsRepositoryImpl
import com.kweku.armah.psm.data.repository.PsmQuizQuestionsRepositoryImpl
import com.kweku.armah.psm.domain.ProfessionalScrumMaster
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
    @ProfessionalScrumMaster
    fun provideQuizRepository(repositoryImpl: PsmQuizQuestionsRepositoryImpl): QuizQuestionsRepository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    @ProfessionalScrumMaster
    fun provideRepository(repositoryImpl: PsmQuestionsRepositoryImpl): QuestionsRepository {
        return repositoryImpl
    }
}
