package com.uriolus.mvvmpractice.domain.usecase

import com.uriolus.mvvmpractice.domain.model.Watch

class StopTimerUseCase(private val watch: Watch) {
    fun exec() = watch.stop()
}