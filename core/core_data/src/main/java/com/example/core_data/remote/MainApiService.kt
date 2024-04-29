package com.example.core_data.remote

import com.example.core_data.model.alltikets.AllTicketsData
import com.example.core_data.model.mainscreen.OfferResponseData
import com.example.core_data.model.search.TicketOfferResponseData
import retrofit2.http.GET

interface MainApiService {

        @GET("uc?export=download&id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
        suspend fun getMainScreen(): OfferResponseData

        @GET("uc?export=download&id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
        suspend fun getSearch(): TicketOfferResponseData

        @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
        suspend fun getAllTickets():AllTicketsData

}