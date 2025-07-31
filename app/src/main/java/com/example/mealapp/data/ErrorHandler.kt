package com.example.mealapp.data

import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.domain.resource.MealError.NO_INTERNET
import okio.IOException


fun Throwable.getErrorStatus(): MealError {
    return when (this) {
        is IOException -> NO_INTERNET
        is NullPointerException -> MealError.NO_MEALS_FOUND
        else -> MealError.UNKNOWN
    }

}