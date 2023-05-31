package com.example.viewmodelexample.main.components

data class MainState(
    val firstEd: String = "",
    val secEd: String = "",
    val result: String = "",
    val toastShowing: Boolean = false
)
