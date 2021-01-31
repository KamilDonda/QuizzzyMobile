package com.example.quiz.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://opentdb.com/"

object QuizService {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: QuizAPI by lazy {
        retrofit.create(QuizAPI::class.java)
    }
}