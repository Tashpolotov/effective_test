package com.example.core_domain.usecase

import com.example.core_domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(private val repository: MainRepository) {

    suspend fun getMainScreen() = repository.getMainScreen()

    suspend fun getSearch() = repository.getSearch()

    suspend fun getAllTickets() = repository.getAllTickets()
}