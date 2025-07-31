package com.example.mealapp.domain.model

data class MealModel(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val measurements: List<String>,
    val instructions: String,
    val youtubeUrl: String?,
    val category: String,
    val area: String,
    val tags: List<String>,
    val sourceUrl: String?
)