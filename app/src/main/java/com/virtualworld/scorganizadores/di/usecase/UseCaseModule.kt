package com.virtualworld.scorganizadores.di.usecase

import com.virtualworld.scorganizadores.domain.usecase.cart.CartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.CartUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteAllUserCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteAllUserCartUseCaseImp
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteUserCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteUserCartUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.cart.UpdateCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.UpdateCartUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.badge.UserCartBadgeUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.category.CategoryUseCase
import com.virtualworld.scorganizadores.domain.usecase.category.CategoryUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.favorite.DeleteFavoriteUseCase
import com.virtualworld.scorganizadores.domain.usecase.favorite.DeleteFavoriteUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.favorite.FavoriteUseCase
import com.virtualworld.scorganizadores.domain.usecase.favorite.FavoriteUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.order.OrderUseCase
import com.virtualworld.scorganizadores.domain.usecase.order.OrderUseCaseImp
import com.virtualworld.scorganizadores.domain.usecase.product.GetAllProductsUseCase
import com.virtualworld.scorganizadores.domain.usecase.product.GetAllProductsUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.product.GetSingleProductUseCase
import com.virtualworld.scorganizadores.domain.usecase.product.GetSingleProductUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.product.SearchProductUseCase
import com.virtualworld.scorganizadores.domain.usecase.product.SearchProductUseCaseImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {


    @Binds
    @ViewModelScoped
    abstract fun bindDeleteAllUserCartUseCase(
        deleteAllUserCartUseCaseImp: DeleteAllUserCartUseCaseImp,
    ): DeleteAllUserCartUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindSetAllOrderUseCase(
        orderUseCaseImp: OrderUseCaseImp,
    ): OrderUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(
        getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl,
    ): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSingleProductUseCase(
        getSingleProductUseCaseImpl: GetSingleProductUseCaseImpl,
    ): GetSingleProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllCategoryUseCase(
        getAllCategoryUseCaseImpl: CategoryUseCaseImpl,
    ): CategoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindCartUseCase(
        cartUseCaseImpl: CartUseCaseImpl,
    ): CartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUserCartUseCase(
        deleteUserCartUseCaseImpl: DeleteUserCartUseCaseImpl,
    ): DeleteUserCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchUseCase(
        searchUseCaseImpl: SearchProductUseCaseImpl,
    ): SearchProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateCartUseCase(
        updateCartUseCaseImpl: UpdateCartUseCaseImpl,
    ): UpdateCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteUseCase(
        favoriteUseCaseImpl: FavoriteUseCaseImpl,
    ): FavoriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFavoriteUseCase(
        deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl,
    ): DeleteFavoriteUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindUserCartBadgeUseCase(
        userCartBadgeUseCaseImpl: UserCartBadgeUseCaseImpl,
    ): UserCartBadgeUseCase
}
