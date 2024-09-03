package com.virtualworld.scorganizadores.ui.mapper // ktlint-disable filename

import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.ui.uiData.DetailProductUiData
import javax.inject.Inject

class DetailProductEntityToUiMapper @Inject constructor() :
    ProductBaseMapper<DetailProductEntity, DetailProductUiData> {
    override fun map(input: DetailProductEntity): DetailProductUiData {
        return DetailProductUiData(
            productId = input.id,
            title = input.title,
            description = input.description,
            price = input.price,
            imageUrl = input.imageUrl,
            rating = input.rating,
        )
    }
}
