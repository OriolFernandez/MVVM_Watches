package com.uriolus.mvvmpractice.domain.usecase

import com.uriolus.mvvmpractice.domain.model.Watch

class StartTimerUseCase(private val watch: Watch) {
    fun exec() = watch.start()
}