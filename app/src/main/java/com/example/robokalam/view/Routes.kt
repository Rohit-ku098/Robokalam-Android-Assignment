package com.example.robokalam.view

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Splash: Routes()
    @Serializable
    object LoginSignup: Routes()
    @Serializable
    object Home: Routes()

    @Serializable
    object AboutPage: Routes()

}