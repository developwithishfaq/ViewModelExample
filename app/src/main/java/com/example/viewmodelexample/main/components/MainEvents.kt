package com.example.viewmodelexample.main.components

sealed class MainEvents {
    data class ADD(val numberOne: String, val numberTwo: String) : MainEvents()
    object TOAST : MainEvents()

}
