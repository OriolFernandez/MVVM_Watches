package com.uriolus.mvvmpractice.domain.usecase

import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class GetAllWatchesUseCase(private val repository: TimeRepository) {
    fun exec(): Flow<List<Watch>> =
        repository.getAllWatches()
}