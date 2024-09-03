package com.virtualworld.scorganizadores.data.dto

//@JsonClass(generateAdapter = true)

data class Product(

  //  @Json(name = "brand")
    val brand: String="",

   // @Json(name = "category")
    val category: String="",

   // @Json(name = "description")
    val description: String="",

  //  @Json(name = "discountPercentage")
    val discountPercentage: Double=0.0,

  //  @Json(name = "id")
    val id: Int=0,

   // @Json(name = "images")
    val images:String="",

  //  @Json(name = "price")
    val price: Int=0,

   // @Json(name = "rating")
    val rating: Double=0.0,

  //  @Json(name = "stock")
    val stock: Int=0,

  //  @Json(name = "thumbnail")
    val thumbnail: String="",

 //   @Json(name = "title")
    val title: String="",
)
