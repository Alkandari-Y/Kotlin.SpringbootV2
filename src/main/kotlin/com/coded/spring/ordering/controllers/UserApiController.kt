package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.dtos.UserResponseDto
import com.coded.spring.ordering.domain.dtos.toDto
import com.coded.spring.ordering.domain.requests.UserCreateRequestDto
import com.coded.spring.ordering.domain.requests.toEntity
import com.coded.spring.ordering.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
class UserApiController
    (private val userService: UserService)
{
    @GetMapping
    fun getUsers() = ResponseEntity.ok(
        userService.findAll()
        .map { it.toDto() }
    )

    @PostMapping
    fun createUser(
        @Valid @RequestBody user: UserCreateRequestDto
    ) = ResponseEntity.ok(
        userService.createUser(user.toEntity()).toDto()
    )

    @GetMapping(path=["/{id}"])
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<UserResponseDto> {
        val user = userService.findById(id)
            ?: return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(user.toDto())
    }
}