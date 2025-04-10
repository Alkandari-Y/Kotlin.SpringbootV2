package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.User


data class UserResponseDto(
    val id: Long,
    val email: String,
    val username: String,
    val name: String
)

fun User.toDto() = UserResponseDto(
    id = id!!,
    email = email,
    username = username,
    name = name
)