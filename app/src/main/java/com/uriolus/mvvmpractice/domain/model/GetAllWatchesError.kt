package com.uriolus.mvvmpractice.domain.model

sealed class GetAllWatchesError {
    object ListEmpty : GetAllWatchesError()
}