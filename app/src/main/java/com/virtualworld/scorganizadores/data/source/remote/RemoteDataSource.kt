package com.virtualworld.scorganizadores.data.source.remote

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.data.dto.Order
import com.virtualworld.scorganizadores.data.dto.Product
import com.virtualworld.scorganizadores.data.dto.Products
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getProductsListFromApi(): Flow<NetworkResponseState<Products>>//find all products

    fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>>//find Products by id

    fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<Products>>//search by query string--------------------------

    fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>>//find all category

    fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<Products>>//find products by category name

     fun setOrderedListForApi(listOrder: List<Order>):  Flow<NetworkResponseState<String>> //send ordered for api

}
