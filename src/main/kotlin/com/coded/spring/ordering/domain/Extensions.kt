package com.coded.spring.ordering.domain

import com.coded.spring.ordering.domain.dtos.*
import com.coded.spring.ordering.domain.entities.MenuItem
import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.entities.User
import com.coded.spring.ordering.domain.projections.RestaurantSummary
import com.coded.spring.ordering.domain.requests.MenuItemCreateRequestDto
import com.coded.spring.ordering.domain.requests.OrderCreateRequestDto
import com.coded.spring.ordering.domain.requests.RestaurantCreateRequestDto
import com.coded.spring.ordering.domain.requests.UserCreateRequestDto

fun UserCreateRequestDto.toEntity() = User(username = this.username, name = this.name)

fun OrderCreateRequestDto.toEntity(user: User, restaurant: Restaurant) =
    Order(
        user=user,
        restaurant = restaurant,
    )

fun RestaurantCreateRequestDto.toEntity() = Restaurant(name = name)

fun MenuItemCreateRequestDto.toEntity(restaurant: Restaurant) =
    MenuItem(name = name, restaurant = restaurant)