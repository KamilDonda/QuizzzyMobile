package com.example.quiz.model.repository

import androidx.lifecycle.LiveData
import com.example.quiz.model.dao.ResultDao
import com.example.quiz.model.entities.Result

class ResultRepository(val dao: ResultDao) {
    val getAllResult: LiveData<List<Result>> = dao.getAllResults()

    suspend fun insert(result: Result) = dao.insert(result)

    fun getResultsWithFilters(categories: List<Int>) = dao.getResultsWithFilters(categories)
}