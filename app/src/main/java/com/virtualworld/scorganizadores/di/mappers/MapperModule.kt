package com.virtualworld.scorganizadores.di.mappers

import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.data.dto.Product
import com.virtualworld.scorganizadores.data.mapper.ProductEntityMapper
import com.virtualworld.scorganizadores.data.mapper.SingleProductEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAllProductsEntityMapper(productEntityMapper: ProductEntityMapper): ProductListMapper<Product, ProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleProductEntityMapper(singleProductEntityMapper: SingleProductEntityMapper): ProductBaseMapper<Product, DetailProductEntity>
}
