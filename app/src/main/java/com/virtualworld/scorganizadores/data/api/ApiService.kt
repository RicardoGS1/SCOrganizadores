package com.virtualworld.scorganizadores.data.api

import com.virtualworld.scorganizadores.data.dto.Orders
import com.virtualworld.scorganizadores.data.dto.Product
import com.virtualworld.scorganizadores.data.dto.Products
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("product")
    suspend fun getProductsListFromApi(): Products

    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<String>


    @GET("products/categories/{categoryName}")
    suspend fun getProductsListByCategoryNameFromApi(@Path("categoryName") categoryName: String): Products

    @GET("product/{id}")
    suspend fun getSingleProductByIdFromApi(@Path("id") productId: Int): Product




    @GET("products/search")
    suspend fun getProductsListBySearchFromApi(@Query("q") query: String): Products



    @POST("products/order")
    suspend fun setSentOrderForApi(@Body order: Orders):Orders



}
