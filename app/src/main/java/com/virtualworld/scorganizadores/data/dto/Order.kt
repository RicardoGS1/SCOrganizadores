package com.virtualworld.scorganizadores.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(

    @Json(name = "name")
    val name: String,

    @Json(name = "address")
    val address: String,

    @Json(name = "phone")
    val phone: String,

    @Json(name = "product_id")
    val product_id: String,

    @Json(name = "product_quantity")
    val product_quantity: String,

    @Json(name = "product_title")
    val product_title: String,

    @Json(name = "product_price")
    val product_price: String,


)