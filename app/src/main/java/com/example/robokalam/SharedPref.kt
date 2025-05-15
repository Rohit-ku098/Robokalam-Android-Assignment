package com.example.robokalam

import android.content.Context
import com.example.robokalam.model.UserModel
import javax.inject.Inject

class SharedPref @Inject constructor(context: Context) {
    private val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun signup(username: String, email: String, password: String) {
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    fun getUser(): UserModel? {
        val username = sharedPref.getString("username", null)
        val email = sharedPref.getString("email", null)
        return if (username != null && email != null) {
            UserModel(username, email)
        } else {
            null
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }
}