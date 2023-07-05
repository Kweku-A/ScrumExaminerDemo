package com.kweku.armah.core.data.database.di

import android.content.Context
import androidx.room.Room
import com.kweku.armah.core.data.database.AppDatabase
import com.kweku.armah.core.data.database.converters.AnswerConverter
import com.kweku.armah.psd.data.database.dao.PsdQuestionsDao
import com.kweku.armah.psd.data.database.dao.PsdQuizDao
import com.kweku.armah.psm.data.database.dao.PsmQuestionsDao
import com.kweku.armah.psm.data.database.dao.PsmQuizDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        answerConverter: AnswerConverter,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "scrum_exams_db",
        )
            .addTypeConverter(answerConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideQuestionsDao(appDatabase: AppDatabase): PsdQuestionsDao {
        return appDatabase.psdQuestionsDao()
    }

    @Singleton
    @Provides
    fun provideQuizDao(appDatabase: AppDatabase): PsdQuizDao {
        return appDatabase.psdQuizDao()
    }

    @Singleton
    @Provides
    fun providePsmQuestionsDao(appDatabase: AppDatabase): PsmQuestionsDao {
        return appDatabase.psmQuestionsDao()
    }

    @Singleton
    @Provides
    fun providePsmQuizDao(appDatabase: AppDatabase): PsmQuizDao {
        return appDatabase.psmQuizDao()
    }
}
