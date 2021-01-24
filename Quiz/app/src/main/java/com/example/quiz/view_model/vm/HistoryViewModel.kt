package com.example.quiz.view_model.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result
import com.example.quiz.model.repository.ResultRepository

class HistoryViewModel (application: Application): AndroidViewModel(application) {
    var allResults: LiveData<List<Result>>

    val listOfIds = MutableLiveData<List<Int>>()

    private var _query: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String>
        get() = _query

    fun setQuery(text: String){
        _query.value = text
    }

    private val repo = ResultRepository(ResultDatabase.getDatabase(application).resultDao())

    init{
        listOfIds.value = null
        allResults = Transformations.switchMap(listOfIds){
            if(it == null) {
                return@switchMap repo.getAllResult
            }
            else {
                return@switchMap repo.getResultsWithFilters(listOfIds.value!!)
            }
        }
    }

    fun setResultsWithFilters(ids: List<Int>){
        listOfIds.value = ids
    }

    fun resetResults() {
        listOfIds.value = null
    }
}