package com.example.mealapp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.mealapp.R
import com.example.mealapp.domain.resource.MealError

@Composable
fun MealError.getMessage(): String {
    val context = LocalContext.current
    return when (this) {
        MealError.NO_MEALS_FOUND -> {
            context.getString(R.string.no_meals_found)
        }

        MealError.NO_INTERNET -> {
            context.getString(R.string.no_internet)
        }

        MealError.UNKNOWN -> {
            ""
        }
    }
}