package com.coded.spring.ordering.restaurants

import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.menus.MenuService
import com.coded.spring.ordering.restaurants.dtos.RestaurantCreateRequestDto
import com.coded.spring.ordering.restaurants.dtos.toEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantApiController(
    private val restaurantService: RestaurantService,
    private val menuService: MenuService,
) {
    @GetMapping
    fun getRestaurants() = restaurantService.findAll()

    @PostMapping
    fun createRestaurant(@RequestBody restaurant: RestaurantCreateRequestDto) =
        restaurantService.create(restaurant.toEntity())

    @GetMapping(path = ["/{id}/menu"])
    fun getRestaurantMenu(@PathVariable("id") id: Long): ResponseEntity<List<MenuBasicInfoProjection>> {
        val restaurant = restaurantService.findById(id)
            ?: return ResponseEntity.badRequest().build()
        val menus = menuService.findByRestaurantId(restaurantId = restaurant.id!!)
        return ResponseEntity.ok(menus)
    }
}