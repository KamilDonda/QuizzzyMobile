package com.example.quiz.model.api

import android.graphics.ColorSpace.Model
import com.example.quiz.model.entities.Quiz
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface QuizAPI {
    @GET("api.php?amount=10&type=multiple")
    fun getQuiz(): Call<Quiz>

    @GET("api.php?amount=10&type=multiple")
    fun getQuizFromCategory(@Query("category") category: Int): Call<Quiz>

    @GET("api.php?amount=10&type=multiple")
    fun getQuizFromCategoryWithDiffcultyLevel(
            @Query("category") category: Int,
            @Query("difficulty") difficulty: String): Call<Quiz>

}