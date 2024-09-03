package com.virtualworld.scorganizadores.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Orders(
    @Json(name = "orders")
    val orders: List<Order>
)
