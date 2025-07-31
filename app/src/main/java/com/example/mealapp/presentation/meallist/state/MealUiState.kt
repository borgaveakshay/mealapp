package com.example.mealapp.presentation.meallist.state

import androidx.compose.runtime.Immutable
import com.example.mealapp.domain.model.MealModel
import com.example.mealapp.domain.resource.MealError
@Immutable
data class MealUiState(
    val isLoading: Boolean = false,
    val meals: List<MealUiItem>? = null,
    val error: MealError? = null

)

data class MealUiItem(
    val id: String,
    val name: String,
    val imageUrl: String,
    val category: String,
    val area: String,
    val instructions: String,
)


fun List<MealModel>.toMealUiItems() = map { mealModel ->
    MealUiItem(
        id = mealModel.id,
        name = mealModel.name,
        imageUrl = mealModel.imageUrl,
        category = mealModel.category,
        area = mealModel.area,
        instructions = mealModel.instructions
    )

}