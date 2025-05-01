package com.coded.ordering.menus.dtos

import com.coded.ordering.domain.entities.MenuEntity
import com.coded.ordering.domain.entities.RestaurantEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class MenuCreateRequestDto(
    @field:NotBlank(message = "Menu Name is required")
    @field:Length(min = 3, message = "Menu Name must be between 3 and 6")
    val name: String,

    @field:NotNull
    @field:Positive(message = "Restaurant ID be positive")
    val restaurantId: Long,
    
    val price: BigDecimal,
)

fun MenuCreateRequestDto.toEntity(restaurant: RestaurantEntity): MenuEntity = MenuEntity(
    name=name,
    restaurant=restaurant,
    price=price
)