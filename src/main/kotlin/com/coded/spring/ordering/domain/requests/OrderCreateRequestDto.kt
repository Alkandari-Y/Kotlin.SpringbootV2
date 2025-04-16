package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.dtos.OrderCreateDto
import com.coded.spring.ordering.domain.dtos.OrderItemCreateDto
import com.coded.spring.ordering.domain.entities.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class OrderItemCreateRequestDto (
    @field:NotBlank(message = "Item ID is required")
    @field:NotNull
    @field:Positive(message = "Item ID must be positive")
    val itemId: Long,
    @field:NotBlank(message = "Amount is required")
    @field:NotNull
    @field:Positive(message = "Amount must be positive")
    val quantity: Int,
)

data class OrderCreateRequestDto(
    @field:NotBlank(message = "User Id is required")
    @field:NotNull
    @field:Positive(message = "User Id is must be positive")
    val userId: Long,

    @field:NotBlank(message = "Restaurant Id is required")
    @field:NotNull
    @field:Positive(message = "Restaurant Id is must be positive")
    val restaurantId: Long,


    val items: List<OrderItemCreateRequestDto>,
)

fun OrderCreateRequestDto.toCreateDto(
    user: User,
    restaurant: Restaurant,
    items: List<OrderItemCreateDto>,
) =  OrderCreateDto(user=user, restaurant=restaurant, items=items)

fun OrderItemCreateRequestDto.toCreateDto(
) = OrderItemCreateDto(itemId=itemId, quantity=quantity)