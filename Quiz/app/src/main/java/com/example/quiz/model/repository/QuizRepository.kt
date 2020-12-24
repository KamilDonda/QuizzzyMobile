package com.example.quiz.model.repository

import com.example.quiz.model.api.QuizService
import com.example.quiz.model.entities.Quiz
import retrofit2.awaitResponse

class QuizRepository {
    companion object{

        suspend fun getQuiz(): Quiz
                = QuizService.api
                .getQuiz().awaitResponse().body()!!

        suspend fun getQuizFromCategory(category: Int): Quiz
                = QuizService.api.getQuizFromCategory(category)
                .awaitResponse().body()!!

        suspend fun getQuizFromCategoryWithDiffcultyLevel(category: Int, diffculty: String): Quiz
                = QuizService.api.getQuizFromCategoryWithDiffcultyLevel(category, diffculty)
                .awaitResponse().body()!!
    }
}