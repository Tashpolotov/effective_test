package com.example.core_data.model.search

import com.example.core_data.model.mainscreen.PriceData

data class TicketOfferData(
    val id: Int,
    val title: String,
    val time_range: List<String>?,
    val price: PriceData
)
