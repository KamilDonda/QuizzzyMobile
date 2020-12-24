package com.example.quiz.view_model.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.entities.Question
import com.example.quiz.model.repository.QuizRepository
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application): AndroidViewModel(application) {

    // our list of questions
    private val _questionList: MutableLiveData<List<Question>> = MutableLiveData()
    val questionList: LiveData<List<Question>>
    get() = _questionList

    init {
        getQuestionList()
    }

    // download data from API and save it into _questionList
    fun getQuestionList(){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _questionList.value = QuizRepository.getQuiz().results
        }
    }
}