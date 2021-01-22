package com.example.quiz.view_model.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result
import com.example.quiz.model.repository.ResultRepository

class HistoryViewModel (application: Application): AndroidViewModel(application) {
    var history: LiveData<List<Result>>
    var _history: LiveData<List<Result>>

    private var _query: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String>
        get() = _query

    fun setQuery(text: String){
        _query.value = text
    }

    private val repo = ResultRepository(ResultDatabase.getDatabase(application).resultDao())

    init{
        history = ResultDatabase.getDatabase(application).resultDao().getAllResults()
        _history = ResultDatabase.getDatabase(application).resultDao().getAllResults()
    }

    fun setResultsWithFilters(categories: List<Int>){
        history = repo.getResultsWithFilters(categories)
        Log.v("TAGGG", "Q: ${history.value}")
    }

    fun resetResults() {
        history = _history
    }
}