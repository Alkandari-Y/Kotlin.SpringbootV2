package com.coded.spring.ordering.users

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.users.dtos.UserResponseDto
import com.coded.spring.ordering.users.dtos.toDto
import com.coded.spring.ordering.users.dtos.UserCreateRequestDto
import com.coded.spring.ordering.users.dtos.toEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name="User API")
@RestController
@RequestMapping("/api/v1/users")
class UserApiController
    (private val userService: UserService)
{

    @Operation(summary = "Get a list of all users for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "List all users",
            content = [
                Content(
                    mediaType = "application/json",
                )
            ]),

        ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @GetMapping
    fun getUsers() = ResponseEntity.ok(
        userService.findAll()
        .map { it.toDto() }
    )

    @Operation(summary = "Get user details by id for  authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Return user details",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserResponseDto::class)
                )
            ]),
        ApiResponse(
            responseCode = "404",
            description = "Not Found",
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
    @GetMapping(path=["/{id}"])
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<UserResponseDto> {
        val user = userService.findById(id)
            ?: return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(user.toDto())
    }
}