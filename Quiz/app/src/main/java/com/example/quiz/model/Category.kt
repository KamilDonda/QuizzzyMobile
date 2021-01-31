package com.example.quiz.model

class Category(val id: Int?, val name: String) {
    companion object {
        val listOfCategories = listOf(
            Category(null, "All"),
            Category(9, "General Knowledge"),
            Category(10, "Books"),
            Category(11, "Film"),
            Category(12, "Music"),
            Category(13, "Musicals & Theatres"),
            Category(14, "Television"),
            Category(15, "Video Games"),
            Category(16, "Board Games"),
            Category(17, "Science & Nature"),
            Category(18, "Computers"),
            Category(19, "Mathematics"),
            Category(20, "Mythology"),
            Category(21, "Sports"),
            Category(22, "Geography"),
            Category(23, "History"),
            Category(24, "Politics"),
            Category(25, "Art"),
            Category(26, "Celebrities"),
            Category(27, "Animals"),
            Category(28, "Vehicles"),
            Category(29, "Comics"),
            Category(30, "Gadgets"),
            Category(31, "Anime & Manga"),
            Category(32, "Cartoon & Animations")
        )
    }
}