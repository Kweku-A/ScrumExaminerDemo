package com.kweku.armah.psm.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.psm.data.database.enitities.PsmQuizEntity

@Dao
interface PsmQuizDao {
    @Query("SELECT * FROM psm_quiz")
    fun getAll(): List<PsmQuizEntity>

    @Query("SELECT COUNT(*) FROM psm_quiz")
    fun getCount(): Int

    @Query("SELECT * FROM psm_quiz WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PsmQuizEntity>

    @Insert
    suspend fun insertAll(users: List<PsmQuizEntity>)

    @Delete
    suspend fun delete(user: PsmQuizEntity)

    @Query("DELETE FROM psm_quiz")
    suspend fun deleteAll()

    @Query("UPDATE psm_quiz SET selectedAnswers=:selectedAnswers WHERE id=:id")
    fun updateQuizWithSelectedAnswers(id: Int, selectedAnswers: List<Answer>)
}
