package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.model.Category

class CategoryViewModel(application: Application): AndroidViewModel(application) {

    private var _currentCategory: MutableLiveData<Category> = MutableLiveData()
    val currentCategory: LiveData<Category>
    get() = _currentCategory

    fun setCurrentCategory(category: Category){
        _currentCategory.value = category
    }
}