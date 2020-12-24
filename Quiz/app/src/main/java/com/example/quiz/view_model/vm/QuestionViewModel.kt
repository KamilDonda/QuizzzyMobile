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

    fun setCategoryAndDifficulty(category: Int? = null, difficulty: String = "All") {
        if (category != null) {
            if(difficulty == "All") {
                getQuestionList(category)
            }
            else {
                getQuestionList(category, difficulty)
            }
        }
        else {
            getQuestionList()
        }
    }

    // download data from API and save it into _questionList
    fun getQuestionList(){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _questionList.value = QuizRepository
                    .getQuiz()
                    .results
        }
    }

    fun getQuestionList(category: Int){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _questionList.value = QuizRepository
                    .getQuizFromCategory(category)
                    .results
        }
    }

    fun getQuestionList(category: Int, difficulty: String){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _questionList.value = QuizRepository
                    .getQuizFromCategoryWithDiffcultyLevel(category, difficulty)
                    .results
        }
    }
}