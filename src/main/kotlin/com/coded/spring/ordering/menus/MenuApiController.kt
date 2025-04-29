package com.coded.spring.ordering.menus

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.menus.dtos.MenuDetailResponse
import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection
import com.coded.spring.ordering.menus.dtos.MenuCreateRequestDto
import com.coded.spring.ordering.menus.dtos.toEntity
import com.coded.spring.ordering.restaurants.RestaurantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Menu API")
@RestController
@RequestMapping("/api/v1/menus")
class MenuApiController(
    private val menuService: MenuService,
    private val restaurantService: RestaurantService
) {

    @Operation(summary = "Receive a list of all menu items available")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Return a list of menu items",
            content = [
                Content(
                    mediaType = "application/json")
            ])
    )
    @GetMapping
    fun getAll(): ResponseEntity<List<MenuEntity>> = ResponseEntity.ok(menuService.findAll())


    @Operation(summary = "Create a new menu item")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successfully created a new menu for authenticated users",
            content = [
                Content(
                    schema = Schema(implementation = MenuCreateRequestDto::class),
                    mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @PostMapping(path=["/create"])
    fun createMenu(
        @RequestBody menuCreateRequestDto: MenuCreateRequestDto
    ): ResponseEntity<MenuEntity> {
        val restaurant: RestaurantEntity = restaurantService.findById(menuCreateRequestDto.restaurantId)
            ?: return ResponseEntity.badRequest().build()
        val newMenu = menuService.create(menuCreateRequestDto.toEntity(restaurant))
        return ResponseEntity(newMenu, HttpStatus.CREATED)
    }

    @Operation(summary = "Get a menu item by id")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successfully returns a menu by id for authenticated users",
            content = [
                Content(
                    schema = Schema(implementation = MenuDetailResponse::class),
                    mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "404",
            description = "Not request",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @GetMapping(path=["/details/{menuId}"])
    fun getMenu(@PathVariable("menuId") menuId: Long): ResponseEntity<MenuDetailResponse> {
        val foundMenu = menuService.findById(menuId)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity(foundMenu, HttpStatus.OK)
    }

    @Operation(summary = "Search all restaurants for menu items")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Returns a menu list by item name or restaurant name for authenticated users",
            content = [
                Content(
                    mediaType = "application/json")
            ]),
    )
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
