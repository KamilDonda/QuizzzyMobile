package com.example.quiz.model.repository

import com.example.quiz.model.api.QuizService
import com.example.quiz.model.entities.Quiz
import retrofit2.awaitResponse

class QuizRepository {
    companion object{

        suspend fun getQuiz(): Quiz
                = QuizService.api.getQuiz().awaitResponse().body()!!
    }
}