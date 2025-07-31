package com.example.mealapp.data.di

import com.example.mealapp.data.api.MealsApi
import com.example.mealapp.data.datastore.MealNetworkStoreImpl
import com.example.mealapp.domain.datastore.MealsNetworkDataStore
import com.example.mealapp.domain.usecases.GetDetailMealUseCase
import com.example.mealapp.domain.usecases.GetMealsUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MealsApi> {
        get<Retrofit>().create(MealsApi::class.java)
    }

    single<MealsNetworkDataStore> {
        MealNetworkStoreImpl(mealsApi = get<MealsApi>())
    }
}

val useCaseModule = module {
    single<GetMealsUseCase> {
        GetMealsUseCase(mealsNetworkDataStore = get<MealsNetworkDataStore>())
    }
    single<GetDetailMealUseCase> {
        GetDetailMealUseCase(mealsNetworkDataStore = get<MealsNetworkDataStore>())
    }
}