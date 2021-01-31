package com.example.quiz.model.repository

import com.example.quiz.model.api.QuizService
import com.example.quiz.model.entities.Data
import retrofit2.awaitResponse

class QuizRepository {
    companion object {

        suspend fun getQuiz(): Data = QuizService.api
            .getQuiz().awaitResponse().body()!!

        suspend fun getQuizFromCategory(category: Int): Data =
            QuizService.api.getQuizFromCategory(category)
                .awaitResponse().body()!!

        suspend fun getQuizFromCategoryWithDiffcultyLevel(category: Int, diffculty: String): Data =
            QuizService.api.getQuizFromCategoryWithDiffcultyLevel(category, diffculty)
                .awaitResponse().body()!!
    }
}