package com.coded.spring.ordering.orders

import com.coded.spring.ordering.orders.dtos.OrderCreateRequestDto
import com.coded.spring.ordering.orders.dtos.toCreateDto
import com.coded.spring.ordering.restaurants.RestaurantService
import com.coded.spring.ordering.users.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    private val orderService: OrderService,
    private val userService: UserService,
    private val restaurantService: RestaurantService,
){
    @GetMapping
    fun getAllOrders() = ResponseEntity.ok(orderService.getAllOrders())

    @PostMapping
    fun createOrder(
        @Valid @RequestBody newOrderDto: OrderCreateRequestDto,
        authentication: Authentication
    ): ResponseEntity<Any> {
        val userDetails = authentication.principal as UserDetails

        val user = userService.findByUserName(userDetails.username)
            ?: return ResponseEntity(HttpStatus.BAD_REQUEST)

        val restaurant = restaurantService.findById(newOrderDto.restaurantId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val order = orderService.create(
            newOrderDto.toCreateDto(
                user,
                restaurant,
                newOrderDto.items.map { it.toCreateDto() }
            )
        )
        return ResponseEntity(order, HttpStatus.OK)
    }
}