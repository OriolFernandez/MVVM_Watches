package com.uriolus.mvvmpractice.data.repository

import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimeRepositoryImpl(private val watch: Watch) : TimeRepository {
    override fun getTick(): Flow<Long> = flow {
        while (true) {
            emit(watch.getTime())
            delay(1000)
        }
    }
}