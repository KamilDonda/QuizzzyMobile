package com.example.quiz.model.api

import com.example.quiz.model.entities.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface QuizAPI {
    @GET("api.php?amount=10&type=multiple")
    fun getQuiz(): Call<Data>

    @GET("api.php?amount=10&type=multiple")
    fun getQuizFromCategory(@Query("category") category: Int): Call<Data>

    @GET("api.php?amount=10&type=multiple")
    fun getQuizFromCategoryWithDifficultyLevel(
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String
    ): Call<Data>

}