package com.example.mealapp.presentation.meallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.domain.resource.Result
import com.example.mealapp.domain.usecases.GetMealsUseCase
import com.example.mealapp.presentation.meallist.state.MealUiState
import com.example.mealapp.presentation.meallist.state.toMealUiItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealViewModel(
    val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _mealUiState = MutableStateFlow(MealUiState())
    val mealUiState = _mealUiState.asStateFlow()

    fun getMeals(searchQuery: String) = viewModelScope.launch {
        getMealsUseCase(searchQuery).collect { result ->
            when (result) {
                is Result.Loading -> {
                    _mealUiState.update { it.copy(isLoading = true) }
                }

                is Result.Success -> {
                    _mealUiState.update {
                        it.copy(
                            isLoading = false,
                            meals = result.value.toMealUiItems()
                        )
                    }
                }

                is Result.Failure -> {
                    _mealUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.error as MealError
                        )
                    }
                }
            }
        }
    }
}