package com.uriolus.mvvmpractice.domain.repository

import arrow.core.Either
import com.uriolus.mvvmpractice.domain.model.*
import kotlinx.coroutines.flow.Flow

interface TimeRepository {
    suspend fun getTickForWatch(id: WatchId): Either<GetWatchErrors, Flow<Long>>
    suspend fun addWatch(watch: Watch): Either<AddWatchErrors, Unit>
    suspend fun getWatch(watchId: WatchId): Either<GetWatchErrors, Watch>
    fun getAllWatches(): Flow<List<Watch>>
}