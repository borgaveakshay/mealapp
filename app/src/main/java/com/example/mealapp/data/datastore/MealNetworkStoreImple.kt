package com.example.mealapp.data.datastore

import com.example.mealapp.data.api.MealsApi
import com.example.mealapp.data.getErrorStatus
import com.example.mealapp.data.models.toMealModel
import com.example.mealapp.domain.datastore.MealsNetworkDataStore
import com.example.mealapp.domain.model.MealModel
import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.domain.resource.Result
import com.example.mealapp.domain.resource.RootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class MealNetworkStoreImpl(
    private val mealsApi: MealsApi
) : MealsNetworkDataStore {
    override suspend fun getMeals(searchQuery: String): Flow<Result<List<MealModel>, RootError>> =
        flow<Result<List<MealModel>, RootError>> {
            val meals = mealsApi.getMeals(searchQuery)
            meals?.meals?.let { meals ->
                emit(Result.Success(meals.map { it.toMealModel() }))
            } ?: run {
                emit(Result.Failure(MealError.NO_MEALS_FOUND))
            }
        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(Result.Failure(it.getErrorStatus()))
        }

    override suspend fun getMealDetails(mealId: String): Flow<Result<List<MealModel>, RootError>> =
        flow {
            val meals = mealsApi.getMealDetails(mealId)
            meals?.meals?.let { meals ->
                emit(Result.Success(meals.map { it.toMealModel() }))
            } ?: run {
                emit(Result.Failure(MealError.NO_MEALS_FOUND))
            }
        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(Result.Failure(it.getErrorStatus()))
        }


}