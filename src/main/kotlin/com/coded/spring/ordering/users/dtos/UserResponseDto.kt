package com.coded.spring.ordering.users.dtos

import com.coded.spring.ordering.domain.entities.UserEntity

data class UserResponseDto(
    val id: Long,
    val email: String,
    val username: String,
    val name: String
)

fun UserEntity.toDto() = UserResponseDto(
    id = id!!,
    email = email,
    username = username,
    name = name
)