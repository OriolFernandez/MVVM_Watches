package com.uriolus.mvvmpractice.data.repository

import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimeRepositoryImpl : TimeRepository {
    override  fun getTimer(): Flow<Long> = flow {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }
}