package com.example.quiz.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.model.entities.Result

@Dao
interface ResultDao {

    @Insert
    suspend fun insert(result: Result)

    @Query("SELECT * FROM result_table")
    fun getAllResults(): LiveData<List<Result>>

    @Query("SELECT * FROM result_table WHERE category IN (:categories)")
    fun getResultsWithFilters(categories: List<Int>): LiveData<List<Result>>
}