package com.uriolus.mvvmpractice.data.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.uriolus.mvvmpractice.data.datasource.WatchDataSource
import com.uriolus.mvvmpractice.domain.model.AddWatchErrors
import com.uriolus.mvvmpractice.domain.model.GetWatchErrors
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.model.WatchId
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class TimeRepositoryDataSource(private val dataSource: WatchDataSource) : TimeRepository {
    override suspend fun getTickForWatch(id: WatchId): Either<GetWatchErrors, Flow<Long>> =
        dataSource.getWatch(id)
            .flatMap { it.getTimeFlow().right() }

    override suspend fun addWatch(watch: Watch): Either<AddWatchErrors, Unit> {
        return dataSource.addWatch(watch)
    }

    override suspend fun getWatch(watchId: WatchId): Either<GetWatchErrors, Watch> {
        return dataSource.getWatch(watchId)
    }

    override fun getAllWatches(): Flow<List<Watch>> {
        return dataSource.getAllWatchesFlow()
    }
}