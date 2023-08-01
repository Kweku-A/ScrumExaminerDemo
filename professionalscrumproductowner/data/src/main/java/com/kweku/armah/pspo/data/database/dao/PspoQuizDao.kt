package com.kweku.armah.pspo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.pspo.data.database.enitities.PspoQuizEntity

@Dao
interface PspoQuizDao {
    @Query("SELECT * FROM pspo_quiz")
    fun getAll(): List<PspoQuizEntity>

    @Query("SELECT COUNT(*) FROM pspo_quiz")
    fun getCount(): Int

    @Query("SELECT * FROM pspo_quiz WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PspoQuizEntity>

    @Insert
    suspend fun insertAll(users: List<PspoQuizEntity>)

    @Delete
    suspend fun delete(user: PspoQuizEntity)

    @Query("DELETE FROM pspo_quiz")
    suspend fun deleteAll()

    @Query("UPDATE pspo_quiz SET selectedAnswers=:selectedAnswers WHERE id=:id")
    fun updateQuizWithSelectedAnswers(id: Int, selectedAnswers: List<Answer>)
}
