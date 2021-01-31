package com.example.quiz.model.repository

import androidx.lifecycle.LiveData
import com.example.quiz.model.dao.ResultDao
import com.example.quiz.model.entities.Result

class ResultRepository(val dao: ResultDao) {
    val getAllResult: LiveData<List<Result>> = dao.getAllResults()

    suspend fun insert(result: Result) = dao.insert(result)

    fun getResultsWithCategories(categories: List<Int>) = dao.getResultsWithCategories(categories)

    fun getResultsWithFilters(categories: List<Int>, levels: List<String>) =
        dao.getResultsWithFilters(categories, levels)

    fun getResultsWithLevels(levels: List<String>) = dao.getResultsWithLevels(levels)
}