package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.User


data class UserCreateRequestDto(
    val name: String,
    val username: String
)

fun UserCreateRequestDto.toEntity() = User(name = name, username = username)
