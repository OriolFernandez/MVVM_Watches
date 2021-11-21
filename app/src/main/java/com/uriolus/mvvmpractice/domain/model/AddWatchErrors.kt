package com.uriolus.mvvmpractice.domain.model

sealed class AddWatchErrors {
    object WatchAlreadyAdded : AddWatchErrors()
    object WatchNotAdded : AddWatchErrors()
}