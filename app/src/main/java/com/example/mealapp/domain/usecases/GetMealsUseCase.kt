package com.example.mealapp.domain.usecases

import com.example.mealapp.domain.datastore.MealsNetworkDataStore
import com.example.mealapp.domain.model.MealModel
import com.example.mealapp.domain.resource.Result
import com.example.mealapp.domain.resource.RootError
import kotlinx.coroutines.flow.Flow

class GetMealsUseCase(
    private val mealsNetworkDataStore: MealsNetworkDataStore
) {
    suspend operator fun invoke(searchQuery: String): Flow<Result<List<MealModel>, RootError>> =
        mealsNetworkDataStore.getMeals(searchQuery)
}