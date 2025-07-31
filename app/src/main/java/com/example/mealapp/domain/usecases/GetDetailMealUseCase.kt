package com.example.mealapp.domain.usecases

import com.example.mealapp.domain.datastore.MealsNetworkDataStore

class GetDetailMealUseCase(
    private val mealsNetworkDataStore: MealsNetworkDataStore
) {
    suspend operator fun invoke(mealId: String) =
        mealsNetworkDataStore.getMealDetails(mealId = mealId)
}