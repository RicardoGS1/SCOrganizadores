package com.virtualworld.scorganizadores.data.mapper

import com.virtualworld.scorganizadores.data.dto.Order
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity
import javax.inject.Inject

class OrderDtoMapper  @Inject constructor()
{
     fun map(input: OrderInfoEntity, input2: List<UserCartEntity>): List<Order>
    {
        return input2.map {
            Order(
                name=input.name,
                phone =input.phone,
                address =input.address,

                product_id = it.productId.toString(),
                product_quantity = it.quantity.toString(),
                product_title = it.title,
                product_price = it.price.toString(),

            )
        }
    }
}
