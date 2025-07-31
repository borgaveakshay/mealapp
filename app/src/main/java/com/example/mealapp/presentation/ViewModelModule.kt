package com.example.mealapp.presentation

import com.example.mealapp.presentation.meallist.MealViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MealViewModel)
}