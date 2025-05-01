package com.coded.ordering.orders

import com.coded.ordering.domain.projections.OrderInfoProjection
import com.coded.ordering.orders.dtos.OrderCreateRequestDto
import com.coded.ordering.orders.dtos.toCreateDto
import com.coded.ordering.restaurants.RestaurantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@Tag(name = "Order API")
@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    private val orderService: OrderService,
    private val restaurantService: RestaurantService,
){

    @Operation(summary = "User get a list of all orders for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Returns a list of all orders",
            content = [
                Content(
                    mediaType = "application/json")
            ]),
    )
    @GetMapping
    fun getAllOrders(@RequestAttribute("userId") userId: Long,
    ): ResponseEntity<List<OrderInfoProjection>> {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(userId))
    }


    @Operation(summary = "Create a new order for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "Successful Created a new order",
            content = [
                Content(
                    mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "404",
            description = "Not Found - Restaurant not found",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @PostMapping
    fun createOrder(
        @Valid @RequestBody newOrderDto: OrderCreateRequestDto,
        @RequestAttribute("userId") userId: Long,
    ): ResponseEntity<Any> {
        val restaurant = restaurantService.findById(newOrderDto.restaurantId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val order = orderService.create(
            newOrderDto.toCreateDto(
                userId,
                restaurant,
                newOrderDto.items.map { it.toCreateDto() }
            )
        )
        return ResponseEntity(order, HttpStatus.CREATED)
    }
}