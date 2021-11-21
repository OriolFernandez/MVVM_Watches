package com.uriolus.mvvmpractice.domain.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.uriolus.mvvmpractice.domain.model.AddWatchErrors
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.repository.TimeRepository

class AddWatchUseCase(private val repository: TimeRepository) {
    suspend fun exec(): Either<AddWatchErrors, Watch> {
        val newWatch = Watch.newWatch()
        return repository.addWatch(newWatch)
            .flatMap { newWatch.right() }
    }
}