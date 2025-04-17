package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.OrderCreateRequestDto
import com.coded.spring.ordering.domain.requests.toCreateDto
import com.coded.spring.ordering.services.OrderService
import com.coded.spring.ordering.services.RestaurantService
import com.coded.spring.ordering.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun createOrder(@Valid @RequestBody newOrderDto: OrderCreateRequestDto): ResponseEntity<Any> {
        println(newOrderDto)
        val user = userService.findById(newOrderDto.userId)
            ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        val restaurant = restaurantService.findById(newOrderDto.restaurantId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        orderService.create(
            newOrderDto.toCreateDto(
                user,
                restaurant,
                newOrderDto.items.map { it.toCreateDto() }
            )
        )
        return ResponseEntity(HttpStatus.OK)
    }
}