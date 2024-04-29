package com.example.core_domain.repository


import com.example.core_domain.model.allteickets.AllTickets
import com.example.core_domain.model.mainscreen.Offer
import com.example.core_domain.model.mainscreen.OfferResponse
import com.example.core_domain.model.search.TicketOfferResponse
import com.example.core_utils.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getMainScreen(): Flow<Resource<OfferResponse>>

    suspend fun getSearch(): Flow<Resource<TicketOfferResponse>>

    suspend fun getAllTickets():Flow<Resource<AllTickets>>

}