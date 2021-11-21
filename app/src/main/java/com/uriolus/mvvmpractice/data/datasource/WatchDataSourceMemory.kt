package com.uriolus.mvvmpractice.data.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.uriolus.mvvmpractice.domain.model.AddWatchErrors
import com.uriolus.mvvmpractice.domain.model.GetWatchErrors
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.model.WatchId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class WatchDataSourceMemory : WatchDataSource {
    private val list: MutableList<Watch> = mutableListOf()
    private val _sharedFlow = MutableSharedFlow<List<Watch>>()


    override suspend fun addWatch(watch: Watch): Either<AddWatchErrors, Unit> {
        return try {
            if (list.add(watch)) {
                _sharedFlow.emit(list)
                Unit.right()
            } else {
                AddWatchErrors.WatchAlreadyAdded.left()
            }
        } catch (e: Exception) {
            AddWatchErrors.WatchNotAdded.left()
        }
    }

    override suspend fun getWatch(watchId: WatchId): Either<GetWatchErrors, Watch> {
        return list.firstOrNull { it.id == watchId }?.right() ?: GetWatchErrors.WatchNotFound.left()
    }

    override suspend fun removeWatch(watchId: WatchId): Either<Unit, Unit> {
        return if (list.removeIf { it.id == watchId }) {
            Unit.right()
        } else {
            Unit.left()
        }
    }

    override fun getAllWatchesFlow(): SharedFlow<List<Watch>> {
        return _sharedFlow
    }
}
