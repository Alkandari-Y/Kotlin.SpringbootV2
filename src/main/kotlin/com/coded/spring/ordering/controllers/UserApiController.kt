package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.UserCreateRequestDto
import com.coded.spring.ordering.domain.toEntity
import com.coded.spring.ordering.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserApiController
    (private val userService: UserService)
{
    @GetMapping
    fun getUsers() = ResponseEntity.ok(userService.getUsers())

    @PostMapping
    fun createUser(
        @RequestBody user: UserCreateRequestDto
    ) = ResponseEntity.ok(
        userService.createUser(user.toEntity())
    )
}