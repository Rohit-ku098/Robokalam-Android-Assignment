package com.example.robokalam.repository

import android.util.Log
import com.example.robokalam.common.ApiResponse
import com.example.robokalam.common.Constants.TAG
import com.example.robokalam.data.local.PortfolioDao
import com.example.robokalam.data.remote.ApiService
import com.example.robokalam.model.Portfolio
import com.example.robokalam.model.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService, private val portfolioDao: PortfolioDao) {
    fun getQuotes() = callbackFlow {
        trySend(ApiResponse.Loading())
        try {
            val  response = apiService.getQuotes()
            trySend(ApiResponse.Success(response))
        } catch (e: Exception) {
            Log.e(TAG, "getQuotes: ${e.message}")
            trySend(ApiResponse.Error(e.message ?: "Unknown error"))
        }
        awaitClose { close() }
    }

    fun insertPortfolio(portfolio: Portfolio) {
        CoroutineScope(Dispatchers.IO).launch {
            portfolioDao.insertPortfolio(portfolio)
        }
    }

    fun updatePortfolio(portfolio: Portfolio) {
        CoroutineScope(Dispatchers.IO).launch {
            portfolioDao.updatePortfolio(portfolio)
        }
    }

    fun getAllPortfolios(): Flow<List<Portfolio>> {
        return portfolioDao.getAllPortfolios()
    }
}