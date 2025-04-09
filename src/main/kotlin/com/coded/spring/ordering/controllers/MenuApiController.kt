package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.MenuItemCreateRequestDto
import com.coded.spring.ordering.domain.entities.MenuItem
import com.coded.spring.ordering.services.MenuItemService
import com.coded.spring.ordering.services.RestaurantService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/menus")
class MenuApiController(

) {
//    @GetMapping
//    fun getMenus() = menuItemRepository.findAll()
//
//    @PostMapping
//    fun addToMenu(@RequestBody menuItem: MenuItemCreateRequestDto): ResponseEntity<MenuItem> {
//        val restaurant = restaurantRepository.findByName(menuItem.restaurantName)
//            ?: return ResponseEntity.badRequest().build()
//
//        val savedMenuItem = menuItemRepository.save(MenuItem(name = menuItem.name, restaurant = restaurant))
//        return ResponseEntity.ok(savedMenuItem)
//    }
}