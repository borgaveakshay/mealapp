package com.example.mealapp.data.api

import com.example.mealapp.data.models.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("search.php")
    suspend fun getMeals(@Query("s") searchQuery: String): MealsResponse?
    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") mealId: String): MealsResponse?
}