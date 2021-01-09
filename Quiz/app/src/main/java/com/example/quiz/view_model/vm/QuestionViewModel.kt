package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.entities.Quiz
import com.example.quiz.model.repository.QuizRepository
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application): AndroidViewModel(application) {

    private var _currentQuizNumber = 0
    val currentQuizNumber: Int
    get() = _currentQuizNumber
    fun IncrementQuizNumber(): Boolean {
        val bool = _currentQuizNumber < _quizList.value!!.size - 1
        if (bool) _currentQuizNumber++
        return bool
    }

    fun ResetQuizNumber(): Int {
        _currentQuizNumber = 0
        return _currentQuizNumber
    }

    // our list of questions
    private val _quizList: MutableLiveData<List<Quiz>> = MutableLiveData()
    val quizList: LiveData<List<Quiz>>
    get() = _quizList

    fun setCategoryAndDifficulty(category: Int? = null, difficulty: String = "all") {
        if (category != null) {
            if(difficulty == "all") {
                setQuizList(category)
            }
            else {
                setQuizList(category, difficulty)
            }
        }
        else {
            setQuizList()
        }
    }

    // download data from API and save it into _questionList
    fun setQuizList(){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _quizList.value = QuizRepository
                    .getQuiz()
                    .results
        }
    }

    fun setQuizList(category: Int){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _quizList.value = QuizRepository
                    .getQuizFromCategory(category)
                    .results
        }
    }

    fun setQuizList(category: Int, difficulty: String){
        viewModelScope.launch {
            // our Quiz has two properties: response_code and results
            // results is a list of Questions
            _quizList.value = QuizRepository
                    .getQuizFromCategoryWithDiffcultyLevel(category, difficulty)
                    .results
        }
    }

    fun getCurrentQuestion()
            = _quizList.value!!.get(_currentQuizNumber).question

    fun getCurrentCorrectAnswer()
            = _quizList.value!!.get(_currentQuizNumber).correct_answer

    fun getCurrentAnswers(): List<String> {
        val list: MutableList<String> = _quizList.value!!.get(_currentQuizNumber).incorrect_answers as MutableList<String>
        list.add(getCurrentCorrectAnswer())
        list.shuffle()
        return list
    }

    fun getCurrentCategory()
            = _quizList.value!!.get(_currentQuizNumber).category
}