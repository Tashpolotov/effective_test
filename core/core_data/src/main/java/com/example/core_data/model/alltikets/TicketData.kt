package com.example.core_data.model.alltikets

data class TicketData(
    val arrival: ArrivalData,
    val badge: String,
    val company: String,
    val departure: DepartureData,
    val hand_luggage: HandLuggageData,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val id: Int,
    val is_exchangable: Boolean,
    val is_returnable: Boolean,
    val luggage: LuggageData,
    val price: PriceXData,
    val provider_name: String
)