package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.OrderCreateRequestDto
import com.coded.spring.ordering.domain.dtos.OrderResponseSummary
import com.coded.spring.ordering.domain.entities.*
//import com.coded.spring.ordering.domain.toDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(

){

//    @GetMapping
//    fun getOrders(): List<OrderResponseSummary> = orderRepository.findOrdersSummaries()
//        .map { it.toDto() }

//    @PostMapping
//    fun createOrder(@RequestBody orderCreateRequestDto: OrderCreateRequestDto)
//    : ResponseEntity<List<MenuItem>> {
//        val customer = userRepository.findByUsername(orderCreateRequestDto.user)
//        val restaurant = restaurantRepository.findByName(orderCreateRequestDto.restaurant)
//        if (customer == null || restaurant == null) return ResponseEntity.badRequest().build()
//        val items = menuItemRepository.findByNameIn(orderCreateRequestDto.items)
//
//        val order = orderRepository.save(Order(restaurant = restaurant, user = customer))
//        orderItemRepository.saveAll(orderCreateRequestDto.items.map { OrderItem(name=it, order=order) })
//
//        return ResponseEntity(items, HttpStatus.OK)
//    }

}