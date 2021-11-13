package com.uriolus.mvvmpractice.di

import com.uriolus.mvvmpractice.data.repository.TimeRepositoryImpl
import com.uriolus.mvvmpractice.domain.model.Watch
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import com.uriolus.mvvmpractice.domain.usecase.GetTickUseCase
import com.uriolus.mvvmpractice.domain.usecase.StartTimerUseCase
import com.uriolus.mvvmpractice.domain.usecase.StopTimerUseCase
import com.uriolus.mvvmpractice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    val watch: Watch = Watch()
    single<TimeRepository> { TimeRepositoryImpl(watch) }
    factory { GetTickUseCase(get()) }
    factory { StopTimerUseCase(watch) }
    factory { StartTimerUseCase(watch) }
    viewModel { MainViewModel(get(), get(), get()) }
}