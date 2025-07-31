package com.example.mealapp.domain.resource

typealias RootError = Error
sealed class Result<out T, out E> {
    data class Success<out T>(val value: T) : Result<T, Nothing>()
    data class Failure<out E>(val error: E) : Result<Nothing, E>()
    data object Loading: Result<Nothing, Nothing>()
}