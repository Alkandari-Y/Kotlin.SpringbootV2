package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.RestaurantCreateRequestDto
import com.coded.spring.ordering.domain.toEntity
import com.coded.spring.ordering.services.RestaurantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantApiController(
    private val restaurantService: RestaurantService
) {
    @GetMapping
    fun getRestaurants() = restaurantService.allSummaries()

    @PostMapping
    fun createRestaurant(@RequestBody restaurant: RestaurantCreateRequestDto) =
        restaurantService.save(restaurant.toEntity())
}