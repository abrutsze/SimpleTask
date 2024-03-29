package com.example.data.di

import com.example.Constants.Companion.BASE_URL
import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.DataRepository
import com.example.data.repository.DataRepositoryImpl
import com.example.data.utils.HeaderInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single { Moshi.Builder().build() }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
            }
            .build()
    }

    single<AllApiService> { get<Retrofit>().create(AllApiService::class.java) }
}

val repositoryModule = module {

    single<DataRepository> { DataRepositoryImpl(get()) }
}