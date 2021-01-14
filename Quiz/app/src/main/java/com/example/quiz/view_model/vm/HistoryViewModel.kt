package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quiz.model.database.ResultDatabase
import com.example.quiz.model.entities.Result

class HistoryViewModel (application: Application): AndroidViewModel(application) {
    val history: LiveData<List<Result>>

    init{
        history = ResultDatabase.getDatabase(application).resultDao().getAllResults()
    }
}