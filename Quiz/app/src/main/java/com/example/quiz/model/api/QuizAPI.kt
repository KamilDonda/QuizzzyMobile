package com.example.quiz.model.api

import com.example.quiz.model.entities.Quiz
import retrofit2.Call
import retrofit2.http.GET

interface QuizAPI {
    @GET("api.php?amount=10&type=multiple")
    fun getQuiz(): Call<Quiz>
}