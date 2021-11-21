package com.uriolus.mvvmpractice.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uriolus.mvvmpractice.databinding.ActivityMainBinding
import com.uriolus.mvvmpractice.presentation.viewmodel.MainViewModel
import com.uriolus.mvvmpractice.presentation.wathceslistview.WatchesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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
        setUpRecyclerView()
        setUpListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getAllWatchesFlow().collect {
                        (binding.watches.adapter as WatchesAdapter).updateWatches(it)
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.watches.layoutManager = linearLayoutManager
        binding.watches.adapter = WatchesAdapter(this,lifecycleScope)
    }

    private fun showTimer1(time: String) {
        binding.timer1.text = time
    }

    private fun setUpListeners() {
        binding.buttonStart.setOnClickListener {
            //viewModel.startTimer1()
        }
        binding.buttonStop.setOnClickListener {
            //viewModel.stopTimer1()
        }
        binding.buttonAdd.setOnClickListener {
            viewModel.addWatch()
        }
    }
}