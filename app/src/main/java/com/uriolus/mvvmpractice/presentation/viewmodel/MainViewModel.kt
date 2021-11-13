package com.uriolus.mvvmpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.uriolus.mvvmpractice.domain.timeutils.Formatter.Companion.formatToSeconds
import com.uriolus.mvvmpractice.domain.usecase.GetTickUseCase
import com.uriolus.mvvmpractice.domain.usecase.StartTimerUseCase
import com.uriolus.mvvmpractice.domain.usecase.StopTimerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel(
    private val getTickUseCase: GetTickUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val startTimerUseCase: StartTimerUseCase
) : ViewModel() {

    fun stopTimer1() {
        stopTimerUseCase.exec()
    }

    fun startTimer1() {
        startTimerUseCase.exec()
    }

    val timerTicker: Flow<String> = getTickUseCase.exec().map { it.formatToSeconds() }
}