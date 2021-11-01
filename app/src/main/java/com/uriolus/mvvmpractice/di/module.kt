package com.uriolus.mvvmpractice.di

import com.uriolus.mvvmpractice.data.repository.TimeRepositoryImpl
import com.uriolus.mvvmpractice.domain.repository.TimeRepository
import com.uriolus.mvvmpractice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module{
    single<TimeRepository> { TimeRepositoryImpl() }
    viewModel { MainViewModel(get()) }
}