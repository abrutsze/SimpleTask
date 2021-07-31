package com.example.domian.di

import com.example.domian.interactors.DataInfoInteractor
import com.example.domian.usecase.DataInfoUseCase
import org.koin.dsl.module

val interactorModule = module {

    single<DataInfoInteractor> { DataInfoUseCase(get()) }
}
