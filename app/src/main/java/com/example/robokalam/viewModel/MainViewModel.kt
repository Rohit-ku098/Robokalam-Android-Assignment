package com.example.robokalam.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.robokalam.common.ApiResponse
import com.example.robokalam.common.Constants.TAG
import com.example.robokalam.model.Portfolio
import com.example.robokalam.model.Quote
import com.example.robokalam.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _quotes = MutableStateFlow<ApiResponse<List<Quote>>>(ApiResponse.Loading())
    val quotes: StateFlow<ApiResponse<List<Quote>>> = _quotes
    private val _portfolio = MutableStateFlow<Portfolio>(
        Portfolio(
            id = 1,
            name = "",
            college = "",
            skills = "",
            projects = Pair("", ""),
        )
    )

    init {
        getAllPortfolios()
        getQuotes()
    }
    val portfolio: StateFlow<Portfolio> = _portfolio


    fun getQuotes() {
        viewModelScope.launch {
            repository.getQuotes().collect {
                _quotes.value = it
            }
        }
    }

    fun insertPortfolio(portfolio: Portfolio) {
        viewModelScope.launch {
            repository.insertPortfolio(portfolio)
        }
    }

    fun updatePortfolio(portfolio: Portfolio) {
        viewModelScope.launch {
            repository.updatePortfolio(portfolio)
        }
    }

    fun getAllPortfolios() {
        viewModelScope.launch {
            repository.getAllPortfolios().collect {
                if(it.isNotEmpty()) {
                    _portfolio.value = it.first()
                    Log.d(TAG, "getAllPortfolios: ${it.first()}")
                }
            }
        }
    }


}