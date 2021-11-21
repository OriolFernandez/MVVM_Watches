package com.uriolus.mvvmpractice.di

import com.uriolus.mvvmpractice.data.datasource.WatchDataSource
import com.uriolus.mvvmpractice.data.datasource.WatchDataSourceMemory
import com.uriolus.mvvmpractice.data.repository.TimeRepositoryDataSource
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import com.uriolus.mvvmpractice.domain.usecase.*
import com.uriolus.mvvmpractice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    val dataSource: WatchDataSource = WatchDataSourceMemory()
    single<TimeRepository> { TimeRepositoryDataSource(dataSource) }
    factory { GetTickUseCase(get()) }
    factory { StopTimerUseCase(get()) }
    factory { StartTimerUseCase(get()) }
    factory { AddWatchUseCase(get()) }
    factory { GetAllWatchesUseCase(get()) }
    viewModel { MainViewModel(
        addWatchUseCase = get(),
        getTickUseCase = get(),
        stopTimerUseCase = get(),
        startTimerUseCase = get(),
        getAllWatchesUseCase = get()
    ) }
}