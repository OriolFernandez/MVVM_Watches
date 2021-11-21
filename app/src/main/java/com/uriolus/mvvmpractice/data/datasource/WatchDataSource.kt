package com.uriolus.mvvmpractice.data.datasource

import arrow.core.Either
import com.uriolus.mvvmpractice.domain.model.AddWatchErrors
import com.uriolus.mvvmpractice.domain.model.GetWatchErrors
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.model.WatchId
import kotlinx.coroutines.flow.Flow

interface WatchDataSource {
    suspend fun addWatch(watch: Watch): Either<AddWatchErrors, Unit>
    suspend fun getWatch(watchId: WatchId): Either<GetWatchErrors, Watch>
    suspend fun removeWatch(watchId: WatchId): Either<Unit, Unit>
    fun getAllWatchesFlow(): Flow<List<Watch>>
}