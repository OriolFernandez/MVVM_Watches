package com.uriolus.mvvmpractice.domain.model

sealed class GetWatchErrors {
    object WatchNotFound : GetWatchErrors()
}