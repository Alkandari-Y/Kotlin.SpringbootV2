package com.coded.ordering.orders.dtos

import com.coded.ordering.domain.entities.*
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class OrderItemCreateRequestDto (
    @field:NotNull
    @field:Positive(message = "Item ID must be positive")
    val itemId: Long,

    @field:NotNull
    @field:Positive(message = "Amount must be positive")
    val quantity: Int,
)

data class OrderCreateRequestDto(
    @field:NotNull
    @field:Positive(message = "Restaurant Id is must be positive")
    val restaurantId: Long,

    val items: List<OrderItemCreateRequestDto>,
)

fun OrderCreateRequestDto.toCreateDto(
    user: Long,
    restaurant: RestaurantEntity,
    items: List<OrderItemCreateDto>,
) =  OrderCreateDto(user=user, restaurant=restaurant, items=items)

fun OrderItemCreateRequestDto.toCreateDto()
    = OrderItemCreateDto(itemId=itemId, quantity=quantity)