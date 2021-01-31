package com.example.quiz.view_model.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.entities.Quiz
import com.example.quiz.model.repository.QuizRepository
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val _timeIsUp = MutableLiveData<Boolean>()
    val timeIsUp: LiveData<Boolean>
        get() = _timeIsUp

    fun setTimeIsUp(bool: Boolean) {
        _timeIsUp.value = bool
    }

    private var _currentQuizNumber = 0
    val currentQuizNumber: Int
        get() = _currentQuizNumber

    fun incrementQuizNumber(): Boolean {
        val bool = _currentQuizNumber < _quizList.value!!.size - 1
        if (bool) _currentQuizNumber++

        resetProgressStatus()
        return bool
    }

    fun resetQuizNumber(): Int {
        _currentQuizNumber = 0
        resetQuizList()
        resetProgressStatus()
        return _currentQuizNumber
    }

    private var _progressStatus: Int = 0
    val progressStatus: Int
        get() = _progressStatus

    fun incrementProgressStatus(time: Int) {
        _progressStatus = time
    }

    private fun resetProgressStatus() {
        _progressStatus = 0
    }

    private val _quizList: MutableLiveData<List<Quiz>> = MutableLiveData()
    val quizList: LiveData<List<Quiz>>
        get() = _quizList

    private fun resetQuizList() {
        _quizList.value = null
    }

    init {
        setTimeIsUp(false)
    }

    fun setCategoryAndDifficulty(category: Int? = null, difficulty: String = "all") {
        if (category != null) {
            if (difficulty == "all") {
                setQuizList(category)
            } else {
                setQuizList(category, difficulty)
            }
        } else {
            setQuizList()
        }
    }

    private fun setQuizList() {
        viewModelScope.launch {
            _quizList.value = QuizRepository
                .getQuiz()
                .results
        }
    }

    private fun setQuizList(category: Int) {
        viewModelScope.launch {
            _quizList.value = QuizRepository
                .getQuizFromCategory(category)
                .results
        }
    }

    private fun setQuizList(category: Int, difficulty: String) {
        viewModelScope.launch {
            _quizList.value = QuizRepository
                .getQuizFromCategoryWithDifficultyLevel(category, difficulty)
                .results
        }
    }

    fun getCurrentQuestion() = _quizList.value!![_currentQuizNumber].question

    fun getCurrentCorrectAnswer() = _quizList.value!![_currentQuizNumber].correct_answer

    fun getCurrentAnswers(): List<String> {
        val list: MutableList<String> =
            _quizList.value!![_currentQuizNumber].incorrect_answers as MutableList<String>
        val correct = getCurrentCorrectAnswer()
        if (!list.contains(correct))
            list.add(correct)
        list.shuffle()
        return list
    }

    fun getCurrentCategory() = _quizList.value!![_currentQuizNumber].category
}