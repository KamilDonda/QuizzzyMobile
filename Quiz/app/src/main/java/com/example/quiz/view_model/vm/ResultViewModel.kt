package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result
import com.example.quiz.model.repository.ResultRepository
import kotlinx.coroutines.launch
import java.util.*

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ResultRepository(ResultDatabase.getDatabase(application).resultDao())

    fun insert(date: Date, category: Int, difficulty: String, result: Int) {
        viewModelScope.launch {
            repo.insert(Result(0, date, category, difficulty, result))
        }
    }

    private var _result = 0
    val result: Int
        get() = _result

    fun incrementResultNumber() {
        _result++
    }

    fun resetResultNumber(): Int {
        _result = 0
        return _result
    }
}