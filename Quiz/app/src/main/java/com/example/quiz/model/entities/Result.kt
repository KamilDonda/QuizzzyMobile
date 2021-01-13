package com.example.quiz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "result_table")
data class Result(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val date: Date,
        val category: Int,
        val difficulty: String,
        val result: Int
)