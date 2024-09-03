package com.virtualworld.scorganizadores.ui.uiData

data class UserCartUiData(
    val userId: String,
    val productId: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val imageUrl: String,
)