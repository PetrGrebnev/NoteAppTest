package com.example.noteapptest

sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    class Error<T>(val e: Throwable) : ResultState<T>()
    class Success<T>(val data: T) : ResultState<T>()
}

