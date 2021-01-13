package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result
import com.example.quiz.model.repository.ResultRepository
import kotlinx.coroutines.launch
import java.util.*

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    val results: LiveData<List<Result>>
    private val repo = ResultRepository(ResultDatabase.getDatabase(application).resultDao())

    init {
        results = ResultDatabase.getDatabase(application).resultDao().getAllResults()
    }

    fun insert(date: Date, category: Int, difficulty: String, result: Int) {
        viewModelScope.launch {
            repo.insert(Result(0, date, category, difficulty, result))
        }
    }

    private var _result = 0
    val result: Int
        get() = _result

    fun IncrementResultNumber() {
        _result++
    }

    fun ResetResultNumber(): Int {
        _result = 0
        return _result
    }
}