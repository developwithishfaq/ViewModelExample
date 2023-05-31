package com.example.viewmodelexample.main

import androidx.lifecycle.ViewModel
import com.example.viewmodelexample.main.components.MainEvents
import com.example.viewmodelexample.main.components.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    val state = MutableStateFlow(MainState())

    fun onEvents(event: MainEvents) {
        when (event) {
            is MainEvents.ADD -> {
                val plus = event.numberOne.toInt() + event.numberTwo.toInt()
                state.update {
                    it.copy(
                        firstEd = event.numberOne,
                        secEd = event.numberTwo,
                        result = plus.toString()
                    )
                }
            }

            MainEvents.TOAST -> {
                state.update {
                    it.copy(
                        toastShowing = !it.toastShowing
                    )
                }
            }
        }
    }

}