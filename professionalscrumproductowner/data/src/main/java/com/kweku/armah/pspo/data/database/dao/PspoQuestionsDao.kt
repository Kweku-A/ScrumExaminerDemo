package com.kweku.armah.pspo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kweku.armah.pspo.data.database.enitities.PspoQuestionEntity

@Dao
interface PspoQuestionsDao {
    @Query("SELECT * FROM pspo_questions")
    fun getAll(): List<PspoQuestionEntity>

    @Query("SELECT COUNT(*) FROM pspo_questions")
    fun getCount(): Int

    @Query("SELECT * FROM pspo_questions WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PspoQuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PspoQuestionEntity>)

    @Delete
    suspend fun delete(user: PspoQuestionEntity)

    @Query("DELETE FROM pspo_questions")
    suspend fun deleteAll()
}
