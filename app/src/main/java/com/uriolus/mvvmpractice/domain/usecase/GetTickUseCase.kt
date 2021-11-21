package com.uriolus.mvvmpractice.domain.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.model.WatchId
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class GetTickUseCase(private val repository: TimeRepository) {
    suspend fun exec(id: WatchId): Either<Unit, Flow<Long>> = repository.getWatch(id)
        .mapLeft { // from error to Unit
        }
        .flatMap { watch: Watch -> watch.getTimeFlow().right() }
}