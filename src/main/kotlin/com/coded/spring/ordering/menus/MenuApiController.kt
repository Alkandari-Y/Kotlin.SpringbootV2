package com.coded.spring.ordering.menus

import com.coded.spring.ordering.menus.dtos.MenuDetailResponse
import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection
import com.coded.spring.ordering.menus.dtos.MenuCreateRequestDto
import com.coded.spring.ordering.menus.dtos.toEntity
import com.coded.spring.ordering.restaurants.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/menus")
class MenuApiController(
    private val menuService: MenuService,
    private val restaurantService: RestaurantService
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<MenuEntity>> = ResponseEntity.ok(menuService.findAll())

    @PostMapping(path=["/create"])
    fun createMenu(
        @RequestBody menuCreateRequestDto: MenuCreateRequestDto
    ): ResponseEntity<MenuEntity> {
        val restaurant: RestaurantEntity = restaurantService.findById(menuCreateRequestDto.restaurantId)
            ?: return ResponseEntity.badRequest().build()
        val newMenu = menuService.create(menuCreateRequestDto.toEntity(restaurant))
        return ResponseEntity(newMenu, HttpStatus.CREATED)
    }

    @GetMapping(path=["/details/{menuId}"])
    fun getMenu(@PathVariable("menuId") menuId: Long): ResponseEntity<MenuDetailResponse> {
        val foundMenu = menuService.findById(menuId)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity(foundMenu, HttpStatus.OK)
    }

    @GetMapping(path=["/search"])
    fun search(
        @RequestParam("restName") restName: String?=null,
        @RequestParam("menuName") foodName: String?=null
    ): ResponseEntity<List<MenuInfoSearchProjection>> {
        return ResponseEntity(
            menuService.searchMenus(foodName, restName),
            HttpStatus.OK
        )
    }
}
