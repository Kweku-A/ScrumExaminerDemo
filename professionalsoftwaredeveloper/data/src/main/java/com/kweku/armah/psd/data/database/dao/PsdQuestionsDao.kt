package com.kweku.armah.psd.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kweku.armah.psd.data.database.enitities.PsdQuestionEntity

@Dao
interface PsdQuestionsDao {
    @Query("SELECT * FROM psd_questions")
    fun getAll(): List<PsdQuestionEntity>

    @Query("SELECT COUNT(*) FROM psd_questions")
    fun getCount(): Int

    @Query("SELECT * FROM psd_questions WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PsdQuestionEntity>

    @Insert
    suspend fun insertAll(users: List<PsdQuestionEntity>)

    @Delete
    suspend fun delete(user: PsdQuestionEntity)

    @Query("DELETE FROM psd_questions")
    suspend fun deleteAll()
}
