package com.kweku.armah.psm.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kweku.armah.psm.data.database.enitities.PsmQuestionEntity

@Dao
interface PsmQuestionsDao {
    @Query("SELECT * FROM psm_questions")
    fun getAll(): List<PsmQuestionEntity>

    @Query("SELECT COUNT(*) FROM psm_questions")
    fun getCount(): Int

    @Query("SELECT * FROM psm_questions WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PsmQuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PsmQuestionEntity>)

    @Delete
    suspend fun delete(user: PsmQuestionEntity)

    @Query("DELETE FROM psm_questions")
    suspend fun deleteAll()
}
