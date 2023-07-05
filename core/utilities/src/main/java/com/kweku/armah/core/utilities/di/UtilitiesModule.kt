package com.kweku.armah.core.utilities.di

import com.kweku.armah.core.utilities.files.FileHelper
import com.kweku.armah.core.utilities.files.FileHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {

    @Singleton
    @Provides
    fun provideFileHelper(fileHelperImpl: FileHelperImpl): FileHelper {
        return fileHelperImpl
    }
}