package com.uriolus.mvvmpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class MainViewModel(private val repository: TimeRepository) : ViewModel() {
    val timerTcker: Flow<Date> = repository.getTimer().map { Date() }
}