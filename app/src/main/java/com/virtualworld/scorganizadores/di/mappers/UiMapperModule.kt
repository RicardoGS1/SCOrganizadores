package com.virtualworld.scorganizadores.di.mappers

import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.domain.entity.product.FavoriteProductEntity
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity

import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.ui.mapper.CartEntityToFavoriteEntityMapper
import com.virtualworld.scorganizadores.ui.mapper.CartEntityToUiMapper
import com.virtualworld.scorganizadores.ui.mapper.CartUiToEntityMapper
import com.virtualworld.scorganizadores.ui.mapper.DetailProductEntityToUiMapper
import com.virtualworld.scorganizadores.ui.mapper.FavoriteEntityToUiMapper
import com.virtualworld.scorganizadores.ui.mapper.FavoriteUiToEntityMapper

import com.virtualworld.scorganizadores.ui.mapper.ProductEntityToUiMapper

import com.virtualworld.scorganizadores.ui.uiData.DetailProductUiData
import com.virtualworld.scorganizadores.ui.uiData.FavoriteUiData

import com.virtualworld.scorganizadores.ui.uiData.ProductUiData
import com.virtualworld.scorganizadores.ui.uiData.UserCartUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UiMapperModule {




    @Binds
    @ViewModelScoped
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductEntityToUiMapper): ProductListMapper<ProductEntity, ProductUiData>


    @Binds
    @ViewModelScoped
    abstract fun bindDetailProductUiMapper(productUiDataMapper: DetailProductEntityToUiMapper): ProductBaseMapper<DetailProductEntity, DetailProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCartUiMapper(cartUiDataMapper: CartEntityToUiMapper): ProductListMapper<UserCartEntity, UserCartUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartUiMapper(singleCartUiDataMapper: CartUiToEntityMapper): ProductBaseMapper<UserCartUiData, UserCartEntity>



    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteItemUiMapper(favoriteEntityToUiMapper: FavoriteEntityToUiMapper): ProductListMapper<FavoriteProductEntity, FavoriteUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleFavoriteItemUiMapper(favoriteUiToEntityMapper: FavoriteUiToEntityMapper): ProductBaseMapper<FavoriteUiData, FavoriteProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartToFavoriteEntityMapper(cartEntityToFavoriteEntityMapper: CartEntityToFavoriteEntityMapper): ProductBaseMapper<UserCartEntity, FavoriteProductEntity>
}
