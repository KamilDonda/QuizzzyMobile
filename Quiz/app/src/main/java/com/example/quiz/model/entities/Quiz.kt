package com.example.quiz.model.entities

data class Quiz(
    val response_code: Int,
    val results: List<Question>
)