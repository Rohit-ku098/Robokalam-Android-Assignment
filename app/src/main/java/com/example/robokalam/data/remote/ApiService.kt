package com.example.robokalam.data.remote

import com.example.robokalam.model.Quote
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    suspend fun getQuotes(): List<Quote>
}