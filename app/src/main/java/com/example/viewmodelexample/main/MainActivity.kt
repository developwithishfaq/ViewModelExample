package com.example.viewmodelexample.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.viewmodelexample.R
import com.example.viewmodelexample.main.components.MainEvents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.addBtn)
        val toast = findViewById<Button>(R.id.toast)
        val numberOne = findViewById<EditText>(R.id.firstOne)
        val numberTwo = findViewById<EditText>(R.id.secondOne)
        val resultTv = findViewById<TextView>(R.id.resultTv)

        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                numberOne.setText(state.firstEd)
                numberTwo.setText(state.secEd)
                resultTv.text = state.result
                if (state.toastShowing) {
                    Toast.makeText(this@MainActivity, state.result, Toast.LENGTH_SHORT).show()
                    viewModel.onEvents(MainEvents.TOAST)
                }
            }
        }

        btn.setOnClickListener {
            viewModel.onEvents(
                MainEvents.ADD(numberOne.text.toString(), numberTwo.text.toString())
            )
        }
        toast.setOnClickListener {
            viewModel.onEvents(MainEvents.TOAST)
        }


    }
}