package com.uriolus.mvvmpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.flatMap
import arrow.core.right
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.usecase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val addWatchUseCase: AddWatchUseCase,
    private val getTickUseCase: GetTickUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val startTimerUseCase: StartTimerUseCase,
    private val getAllWatchesUseCase: GetAllWatchesUseCase
) : ViewModel() {
    fun getAllWatchesFlow(): Flow<List<Watch>> = getAllWatchesUseCase.exec()

    fun addWatch() {
        viewModelScope.launch {
            addWatchUseCase.exec()
                .flatMap {
                    it.start()
                    it.right()
                }
        }
    }
}