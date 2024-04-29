package com.example.core_data.repository

import com.example.core_data.mapper.toAllTickets
import com.example.core_data.mapper.toOfferResponse
import com.example.core_data.mapper.toTicket
import com.example.core_data.remote.MainApiService
import com.example.core_domain.model.allteickets.AllTickets
import com.example.core_domain.model.mainscreen.Offer
import com.example.core_domain.model.search.TicketOfferResponse
import com.example.core_domain.repository.MainRepository
import com.example.core_utils.Resource
import com.example.core_utils.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: MainApiService):MainRepository, BaseRepository() {
    override suspend fun getMainScreen() = doRequest {
        apiService.getMainScreen().toOfferResponse()
    }

    override suspend fun getSearch() = doRequest {
        apiService.getSearch().toTicket()
    }

    override suspend fun getAllTickets() = doRequest {
        apiService.getAllTickets().toAllTickets()
    }
}