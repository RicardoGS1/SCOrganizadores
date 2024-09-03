package com.virtualworld.scorganizadores.ui.mapper

import com.virtualworld.scorganizadores.ui.uiData.ProductUiData
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import javax.inject.Inject

class ProductEntityToUiMapper @Inject constructor() : ProductListMapper<ProductEntity, ProductUiData>
{
    override fun map(input: List<ProductEntity>): List<ProductUiData>
    {
        return input.map {
            ProductUiData(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                rating = it.rating,
            )
        }
    }
}
