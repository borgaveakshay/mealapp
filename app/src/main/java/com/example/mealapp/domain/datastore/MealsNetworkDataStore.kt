package com.example.mealapp.domain.datastore

import com.example.mealapp.domain.resource.Result
import com.example.mealapp.domain.resource.RootError
import com.example.mealapp.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

interface MealsNetworkDataStore {
    suspend fun getMeals(searchQuery: String): Flow<Result<List<MealModel>, RootError>>
    suspend fun getMealDetails(mealId: String): Flow<Result<List<MealModel>, RootError>>

}