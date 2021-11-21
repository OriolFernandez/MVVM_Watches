package com.uriolus.mvvmpractice.domain.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.model.WatchId
import com.uriolus.mvvmpractice.domain.repository.TimeRepository

class StopTimerUseCase(private val repository: TimeRepository) {
    suspend fun exec(id: WatchId): Either<Unit, Unit> = repository.getWatch(id)
        .mapLeft { // from error to Unit
        }
        .flatMap { watch: Watch ->
            watch.stop()
            Unit.right()
        }
}