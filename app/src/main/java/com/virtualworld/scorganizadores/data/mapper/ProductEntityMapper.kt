package com.virtualworld.scorganizadores.data.mapper

import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.data.dto.Product
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() : ProductListMapper<Product, ProductEntity>
{
    override fun map(input: List<Product>): List<ProductEntity>
    {
        return input.map {
            ProductEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price.toString(),
                imageUrl = it.images,
                rating = it.rating,
            )
        }
    }
}
