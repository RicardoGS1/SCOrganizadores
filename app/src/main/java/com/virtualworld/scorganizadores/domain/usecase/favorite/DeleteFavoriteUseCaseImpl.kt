package com.virtualworld.scorganizadores.domain.usecase.favorite

import com.virtualworld.scorganizadores.domain.entity.product.FavoriteProductEntity
import com.virtualworld.scorganizadores.data.repository.LocalRepository
import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : DeleteFavoriteUseCase {
    override suspend fun invoke(favoriteProductEntity: FavoriteProductEntity) {
        repository.deleteFavoriteProduct(favoriteProductEntity)
    }
}
