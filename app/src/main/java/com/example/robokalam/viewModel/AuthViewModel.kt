package com.example.robokalam.viewModel

import androidx.lifecycle.ViewModel
import com.example.robokalam.BaseApplication
import com.example.robokalam.SharedPref
import com.example.robokalam.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val sharedPref: SharedPref): ViewModel() {

    private val _authStatus = MutableStateFlow<Boolean>(false)
    val authStatus = _authStatus.asStateFlow()

    private val _currentUser = MutableStateFlow<UserModel?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        _authStatus.value = sharedPref.isLoggedIn()
    }

    fun getCurrentUser() {
        _currentUser.value = sharedPref.getUser()
    }

    fun signup(username: String, email: String, password: String) {
        sharedPref.signup(username = username, email = email, password = password)
        sharedPref.setLoggedIn(isLoggedIn = true)
        _authStatus.value = true
    }


    fun logout() {
        sharedPref.setLoggedIn(isLoggedIn = false)
        _authStatus.value = false
    }
}