package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.entities.Restaurant
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class MenuCreateRequestDto(
    @field:NotBlank(message = "Menu Name is required")
    @field:NotNull
    @field:Length(min = 3, message = "Menu Name must be between 3 and 6")
    val name: String,
    @field:NotBlank(message = "Restaurant ID is required")
    @field:NotNull
    @field:Positive(message = "Restaurant ID be positive")
    val restaurantId: Long,
    val price: BigDecimal,
)

fun MenuCreateRequestDto.toEntity(restaurant: Restaurant): Menu = Menu(
    name=name,
    restaurant=restaurant,
    price=price
)