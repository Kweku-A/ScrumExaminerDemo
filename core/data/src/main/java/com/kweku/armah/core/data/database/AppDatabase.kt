package com.kweku.armah.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kweku.armah.core.data.database.converters.AnswerConverter
import com.kweku.armah.psd.data.database.dao.PsdQuestionsDao
import com.kweku.armah.psd.data.database.dao.PsdQuizDao
import com.kweku.armah.psd.data.database.enitities.PsdQuestionEntity
import com.kweku.armah.psd.data.database.enitities.PsdQuizEntity
import com.kweku.armah.psm.data.database.dao.PsmQuestionsDao
import com.kweku.armah.psm.data.database.dao.PsmQuizDao
import com.kweku.armah.psm.data.database.enitities.PsmQuestionEntity
import com.kweku.armah.psm.data.database.enitities.PsmQuizEntity
import com.kweku.armah.pspo.data.database.dao.PspoQuestionsDao
import com.kweku.armah.pspo.data.database.dao.PspoQuizDao
import com.kweku.armah.pspo.data.database.enitities.PspoQuestionEntity
import com.kweku.armah.pspo.data.database.enitities.PspoQuizEntity
import javax.inject.Singleton

@Database(
    entities = [
        PsdQuestionEntity::class, PsdQuizEntity::class, PsmQuestionEntity::class,
        PsmQuizEntity::class, PspoQuestionEntity::class, PspoQuizEntity::class,
    ],
    version = 1,
)
@TypeConverters(AnswerConverter::class)
@Singleton
abstract class AppDatabase : RoomDatabase() {
    abstract fun psdQuestionsDao(): PsdQuestionsDao
    abstract fun psdQuizDao(): PsdQuizDao

    abstract fun psmQuestionsDao(): PsmQuestionsDao
    abstract fun psmQuizDao(): PsmQuizDao

    abstract fun pspoQuestionsDao(): PspoQuestionsDao
    abstract fun pspoQuizDao(): PspoQuizDao
}
