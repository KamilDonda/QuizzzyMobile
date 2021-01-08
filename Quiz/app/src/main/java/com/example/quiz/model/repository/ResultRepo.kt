package com.example.quiz.model.repository

import androidx.lifecycle.LiveData
import com.example.quiz.model.dao.ResultDao
import com.example.quiz.model.entities.Result

class ResultRepo(val dao: ResultDao) {
    val getAllResult: LiveData<List<Result>> = dao.getAllResults()

    suspend fun insert(result: Result) = dao.insert(result)
}