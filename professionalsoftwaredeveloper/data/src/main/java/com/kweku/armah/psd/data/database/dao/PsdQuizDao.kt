package com.kweku.armah.psd.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.psd.data.database.enitities.PsdQuizEntity

@Dao
interface PsdQuizDao {
    @Query("SELECT * FROM psd_quiz")
    fun getAll(): List<PsdQuizEntity>

    @Query("SELECT COUNT(*) FROM psd_quiz")
    fun getCount(): Int

    @Query("SELECT * FROM psd_quiz WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PsdQuizEntity>

    @Insert
    suspend fun insertAll(users: List<PsdQuizEntity>)

    @Delete
    suspend fun delete(user: PsdQuizEntity)

    @Query("DELETE FROM psd_quiz")
    suspend fun deleteAll()

    @Query("UPDATE psd_quiz SET selectedAnswers=:selectedAnswers WHERE id=:id")
    fun updateQuizWithSelectedAnswers(id: Int, selectedAnswers: List<Answer>)
}
