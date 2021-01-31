package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DifficultyLevelViewModel(application: Application) : AndroidViewModel(application) {

    private var _currentDifficultyLevel: MutableLiveData<String> = MutableLiveData()
    val currentDifficultyLevel: LiveData<String>
        get() = _currentDifficultyLevel

    fun setCurrentDifficultyLevel(level: String) {
        _currentDifficultyLevel.value = level
    }
}