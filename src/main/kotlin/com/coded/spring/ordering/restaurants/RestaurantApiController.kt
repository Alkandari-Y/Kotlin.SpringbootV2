package com.coded.spring.ordering.restaurants

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.menus.MenuService
import com.coded.spring.ordering.profiles.dtos.ProfileResponseDto
import com.coded.spring.ordering.restaurants.dtos.RestaurantCreateRequestDto
import com.coded.spring.ordering.restaurants.dtos.toEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Restaurants Api")
@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantApiController(
    private val restaurantService: RestaurantService,
    private val menuService: MenuService,
) {

    @Operation(summary = "Get all restaurants for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Return all restaurants",
            content = [
                Content(
                    mediaType = "application/json",
                )
            ]),
    )
    @GetMapping
    fun getRestaurants() = restaurantService.findAll()

    @Operation(summary = "Create a new restaurant for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "Create a new restaurant",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = RestaurantEntity::class)
                )
            ]),

        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @PostMapping
    fun createRestaurant(@RequestBody restaurant: RestaurantCreateRequestDto) =
        restaurantService.create(restaurant.toEntity())

    @Operation(summary = "Get menu items for restaurant by id for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Get restaurant's menu items",
            content = [
                Content(
                    mediaType = "application/json",
                )
            ]),

        ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @GetMapping(path = ["/{id}/menu"])
    fun getRestaurantMenu(@PathVariable("id") id: Long): ResponseEntity<List<MenuBasicInfoProjection>> {
        val restaurant = restaurantService.findById(id)
            ?: return ResponseEntity.badRequest().build()
        val menus = menuService.findByRestaurantId(restaurantId = restaurant.id!!)
        return ResponseEntity.ok(menus)
    }
}