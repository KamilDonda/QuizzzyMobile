package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ResultViewModel(application: Application): AndroidViewModel(application) {
    private var _result = 0
    val result: Int
    get() = _result
    fun IncrementResultNumber(): Int {
        return _result++
    }
}