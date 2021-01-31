package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result
import com.example.quiz.model.repository.ResultRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    var allResults: LiveData<List<Result>>

    private val listOfIds = MutableLiveData<List<Int>>()
    private val listOfLevels = MutableLiveData<List<String>>()

    private var _query: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String>
        get() = _query

    fun setQuery(text: String) {
        _query.value = text
    }

    val diffs = mutableSetOf("Easy", "Medium", "Hard", "All")

    private val repo = ResultRepository(ResultDatabase.getDatabase(application).resultDao())

    private val combinedValues = MediatorLiveData<Pair<List<Int>?, List<String>?>>().apply {
        addSource(listOfIds) {
            value = Pair(it, listOfLevels.value)
        }
        addSource(listOfLevels) {
            value = Pair(listOfIds.value, it)
        }
    }

    init {
        listOfIds.value = null
        listOfLevels.value = diffs.toList()
        allResults = Transformations.switchMap(combinedValues) {
            val categories = it.first
            val levels = it.second
            if (categories == null) {
                if (levels == null)
                    return@switchMap repo.getAllResult
                else
                    return@switchMap repo.getResultsWithLevels(listOfLevels.value!!)
            } else {
                if (levels == null)
                    return@switchMap repo.getResultsWithCategories(listOfIds.value!!)
                else
                    return@switchMap repo.getResultsWithFilters(
                        listOfIds.value!!,
                        listOfLevels.value!!
                    )
            }
        }
    }

    fun setResultsWithCategories(ids: List<Int>) {
        listOfIds.value = ids
    }

    fun setResultsWithLevels(levels: List<String>) {
        listOfLevels.value = levels
    }

    fun resetResults() {
        listOfIds.value = null
    }
}