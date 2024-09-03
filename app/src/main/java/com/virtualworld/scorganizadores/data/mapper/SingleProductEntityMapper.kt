package com.virtualworld.scorganizadores.data.mapper

import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.data.dto.Product
import javax.inject.Inject

class SingleProductEntityMapper @Inject constructor() :
    ProductBaseMapper<Product, DetailProductEntity> {
    override fun map(input: Product): DetailProductEntity {
        return DetailProductEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price.toString(),
            imageUrl = input.images,
            rating = input.rating.toString(),
        )
    }
}
