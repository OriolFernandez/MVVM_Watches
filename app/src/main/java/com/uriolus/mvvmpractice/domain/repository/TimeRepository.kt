package com.uriolus.mvvmpractice.domain.repository

import kotlinx.coroutines.flow.Flow


interface TimeRepository {
     fun getTimer(): Flow<Long>
}