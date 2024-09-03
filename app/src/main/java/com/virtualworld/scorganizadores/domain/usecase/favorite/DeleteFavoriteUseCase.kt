package com.virtualworld.scorganizadores.domain.usecase.favorite

import com.virtualworld.scorganizadores.domain.entity.product.FavoriteProductEntity

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(favoriteProductEntity: FavoriteProductEntity)
}