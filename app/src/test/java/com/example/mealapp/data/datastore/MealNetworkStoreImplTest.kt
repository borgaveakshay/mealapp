package com.example.mealapp.data.datastore

import com.example.mealapp.MealMockUtils
import com.example.mealapp.data.api.MealsApi
import com.example.mealapp.data.models.toMealModel
import com.example.mealapp.domain.datastore.MealsNetworkDataStore
import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.domain.resource.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test

class MealNetworkStoreImplTest {

    private lateinit var mealNetworkStore: MealsNetworkDataStore
    private val mockMealsApi = mockk<MealsApi>()

    @Before
    fun setUp() {
        mealNetworkStore = MealNetworkStoreImpl(mockMealsApi)
    }

    @Test
    fun `when data store returns success then return success result`() = runTest {
        //GIVEN
        val givenMeals = MealMockUtils.getMealNetworkResponse()
        val domainMeals = givenMeals.meals.map { it.toMealModel() }
        coEvery { mockMealsApi.getMeals(any()) } returns givenMeals
        //WHEN
        val result = mealNetworkStore.getMeals("any")
        //THEN
        result.collect {
            when {
                it is Result.Success -> assert(it.value == domainMeals)
            }
        }
    }

    @Test
    fun `when data store returns failure then return failure result`() = runTest {
        //GIVEN
        val error = IOException("Network Error")
        coEvery { mockMealsApi.getMeals(any()) } throws error
        //WHEN
        val result = mealNetworkStore.getMeals("any")
        //THEN
        result.collect {
            when {
                it is Result.Failure -> assert(it.error == MealError.NO_INTERNET)
            }
        }
    }

    @Test
    fun `when data store returns loading then return loading result`() = runTest {
        //GIVEN
        coEvery { mockMealsApi.getMeals(any()) } returns null
        //WHEN
        val result = mealNetworkStore.getMeals("any")
        //THEN
        result.collect {
            when {
                it is Result.Loading -> assert(true)
            }
        }
    }

    @Test
    fun `when data store returns empty list then return failure result`() = runTest {
        //GIVEN
        coEvery { mockMealsApi.getMeals(any()) } returns null
        //WHEN
        val result = mealNetworkStore.getMeals("any")
        //THEN
        result.collect {
            when {
                it is Result.Failure -> assert(it.error == MealError.NO_MEALS_FOUND)
            }
        }
    }
}