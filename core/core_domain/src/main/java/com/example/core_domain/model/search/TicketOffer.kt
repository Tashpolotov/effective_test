package com.example.core_domain.model.search

import com.example.core_domain.model.mainscreen.Price

data class TicketOffer(
    val id: Int,
    val title: String,
    val time_range: List<String>?,
    val price: Price
)
