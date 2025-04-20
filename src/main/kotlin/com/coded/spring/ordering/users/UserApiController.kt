package com.coded.spring.ordering.users

import com.coded.spring.ordering.users.dtos.UserResponseDto
import com.coded.spring.ordering.users.dtos.toDto
import com.coded.spring.ordering.users.dtos.UserCreateRequestDto
import com.coded.spring.ordering.users.dtos.toEntity
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