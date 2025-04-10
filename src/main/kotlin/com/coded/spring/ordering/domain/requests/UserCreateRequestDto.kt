package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.User


data class UserCreateRequestDto(
    val name: String,
    val username: String,
    val email: String,
    val password: String
)

fun UserCreateRequestDto.toEntity() = User(
    name = name,
    username = username,
    email = email,
    password = password
)
