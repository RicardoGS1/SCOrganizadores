package com.virtualworld.scorganizadores.data.repository

import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.data.dto.Product
import com.virtualworld.scorganizadores.data.mapper.OrderDtoMapper
import com.virtualworld.scorganizadores.data.source.remote.RemoteDataSource
import com.virtualworld.scorganizadores.di.coroutine.IoDispatcher
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val allProductsMapper: ProductListMapper<Product, ProductEntity>,
    private val singleProductMapper: ProductBaseMapper<Product, DetailProductEntity>,
    private val postOrdersMapper: OrderDtoMapper,
) : RemoteRepository
{


    override fun getProductsListFromApi(): Flow<NetworkResponseState<List<ProductEntity>>>
    {

        return remoteDataSource.getProductsListFromApi().map { response ->
           println(response.toString())

            when (response)
            {

                is NetworkResponseState.Loading -> NetworkResponseState.Loading

                is NetworkResponseState.Success -> {
                    NetworkResponseState.Success(allProductsMapper.map(response.result.products))

                }


                is NetworkResponseState.Error -> NetworkResponseState.Error(response.exception)
            }
        }.flowOn(ioDispatcher)


    }


    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>>
    {
        return remoteDataSource.getAllCategoriesListFromApi().map {
            when (it)
            {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }


    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<DetailProductEntity>>
    {
        return remoteDataSource.getSingleProductByIdFromApi(productId).map {
            when (it)
            {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(singleProductMapper.map(it.result))
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }

    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<List<ProductEntity>>>
    {
        return remoteDataSource.getProductsListBySearchFromApi(query).map {
            when (it)
            {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(allProductsMapper.map(it.result.products))
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }


    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>>
    {
        return remoteDataSource.getProductsListByCategoryNameFromApi(categoryName).map {
            when (it)
            {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(allProductsMapper.map(it.result.products))
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun setOrderForApi(
        orderInfoEntity: OrderInfoEntity,
        listCart: List<UserCartEntity>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
        onLoading: () -> Unit,
    )
    {




        remoteDataSource.setOrderedListForApi(postOrdersMapper.map(orderInfoEntity, listCart)).collect {

            when (it)
            {
                is NetworkResponseState.Loading -> onLoading()
                is NetworkResponseState.Success -> onSuccess()
                is NetworkResponseState.Error -> onFailure(it.exception.message.toString())
            }
        }


    }
}
