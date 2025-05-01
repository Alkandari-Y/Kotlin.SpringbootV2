package com.coded.authentication.users.dtos

import com.coded.authentication.users.UserEntity
data class UserResponseDto(
    val id: Long,
    val email: String,
    val username: String,
    val name: String
)

fun com.coded.authentication.users.UserEntity.toDto() = UserResponseDto(
    id = id!!,
    email = email,
    username = username,
    name = name
)