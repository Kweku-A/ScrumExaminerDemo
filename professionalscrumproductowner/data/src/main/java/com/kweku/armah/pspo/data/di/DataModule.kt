package com.kweku.armah.pspo.data.di

import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.pspo.data.repository.PspoQuestionsRepositoryImpl
import com.kweku.armah.pspo.data.repository.PspoQuizQuestionsRepositoryImpl
import com.kweku.armah.pspo.domain.ProfessionalScrumProductOwner
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
    @ProfessionalScrumProductOwner
    fun provideQuizRepository(repositoryImpl: PspoQuizQuestionsRepositoryImpl): QuizQuestionsRepository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    @ProfessionalScrumProductOwner
    fun provideRepository(repositoryImpl: PspoQuestionsRepositoryImpl): QuestionsRepository {
        return repositoryImpl
    }
}
