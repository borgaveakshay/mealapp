package com.example.mealapp.presentation

import android.app.Application
import com.example.mealapp.data.di.networkModule
import com.example.mealapp.data.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MealApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MealApp)
            modules(
                networkModule,
                useCaseModule,
                viewModelModule
            )
        }

    }
}