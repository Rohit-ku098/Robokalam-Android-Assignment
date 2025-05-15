package com.example.robokalam.common

sealed class ApiResponse<T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val message: String, val data: T? = null) : ApiResponse<T>()
    class Loading<T> : ApiResponse<T>()
}