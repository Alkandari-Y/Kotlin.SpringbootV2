package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.requests.MenuCreateRequestDto
import com.coded.spring.ordering.domain.requests.toEntity
import com.coded.spring.ordering.repositories.MenuRepository
import com.coded.spring.ordering.services.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/menus")
class MenuApiController(
    private val menuRepository: MenuRepository,
    private val restaurantService: RestaurantService
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<Menu>> = ResponseEntity.ok(menuRepository.findAll())

    @PostMapping
    fun create(
        @RequestBody menuCreateRequestDto: MenuCreateRequestDto
    ): ResponseEntity<Menu> {
        val restaurant: Restaurant = restaurantService.findById(menuCreateRequestDto.restaurantId)
            ?: return ResponseEntity.badRequest().build()
        val newMenu = menuRepository.save(menuCreateRequestDto.toEntity(restaurant))
        return ResponseEntity(newMenu, HttpStatus.CREATED)
    }
}