package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.entities.User
import com.coded.spring.ordering.domain.requests.UserCreateRequestDto
import com.coded.spring.ordering.domain.requests.toEntity
import com.coded.spring.ordering.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserApiController
    (private val userService: UserService)
{
    @GetMapping
    fun getUsers() = ResponseEntity.ok(userService.findAll())

    @PostMapping
    fun createUser(
        @RequestBody user: UserCreateRequestDto
    ) = ResponseEntity.ok(
        userService.createUser(user.toEntity())
    )

    @GetMapping(path=["/{id}"])
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<User> {
        println(id)
        val user = userService.findById(id)
        println(user)
        if (user == null) return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(user)
    }
}