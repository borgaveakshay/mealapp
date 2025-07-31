package com.example.mealapp.domain.usecases

import com.example.mealapp.MealMockUtils
import com.example.mealapp.domain.datastore.MealsNetworkDataStore
import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.domain.resource.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetMealsUseCaseTest {

    private lateinit var getMealsUseCase: GetMealsUseCase
    private val mealsNetworkDataStore = mockk<MealsNetworkDataStore>()

    @Before
    fun setUp() {
        getMealsUseCase = GetMealsUseCase(mealsNetworkDataStore)
    }

    @Test
    fun `when get meal api returns success then return success result`() = runTest {
        //GIVEN
        val meals = MealMockUtils.getMockMeals()
        coEvery { mealsNetworkDataStore.getMeals(any()) } returns flow {
            emit(Result.Success(meals))
        }
        // WHEN
        val result = getMealsUseCase("any")
        //THEN
        result.collect {
            assert(it is Result.Success)
            assert((it as Result.Success).value == meals)
        }
    }

    @Test
    fun `when get meal api returns failure then return failure result`() = runTest {
        //GIVEN
        val error = MealError.NO_MEALS_FOUND
        coEvery { mealsNetworkDataStore.getMeals(any()) } returns flow {
            emit(Result.Failure(error))
        }
        //WHEN
        val result = getMealsUseCase("any")
        //THEN
        result.collect {
            assert(it is Result.Failure)
            assert((it as Result.Failure).error == error)
        }

    }

    @Test
    fun `when get meal api returns loading then return loading result`() = runTest {
        //GIVEN
        coEvery { mealsNetworkDataStore.getMeals(any()) } returns flow {
            emit(Result.Loading)
        }
        //WHEN
        val result = getMealsUseCase("any")
        //THEN
        result.collect {
            assert(it is Result.Loading)
        }
    }

}