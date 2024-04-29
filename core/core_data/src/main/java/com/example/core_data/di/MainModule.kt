package com.example.core_data.di

import com.example.core_data.remote.MainApiService
import com.example.core_data.repository.MainRepositoryImpl
import com.example.core_domain.repository.MainRepository
import com.example.core_domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): MainApiService {
        return retrofit.create(MainApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: MainApiService):MainRepository{
        return MainRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository:MainRepository):MainUseCase{
        return MainUseCase(repository)
    }
}