package com.uriolus.mvvmpractice.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.uriolus.mvvmpractice.databinding.ActivityMainBinding
import com.uriolus.mvvmpractice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.timerTicker.collect {
                    showTimer1(it)
                }
            }
        }
    }

    private fun showTimer1(time: String) {
        binding.timer1.text = time
    }

    private fun setUpListeners() {
        binding.buttonStart.setOnClickListener {
            viewModel.startTimer1()
        }
        binding.buttonStop.setOnClickListener {
            viewModel.stopTimer1()
        }
    }
}