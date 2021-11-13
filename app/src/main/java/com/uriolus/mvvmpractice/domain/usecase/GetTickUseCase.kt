package com.uriolus.mvvmpractice.domain.usecase

import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class GetTickUseCase(private val repository: TimeRepository) {
    fun exec(): Flow<Long> =
        repository.getTick()
}