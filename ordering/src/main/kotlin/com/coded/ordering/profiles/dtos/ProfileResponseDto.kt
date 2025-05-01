package com.coded.ordering.profiles.dtos

import com.coded.ordering.domain.entities.ProfileEntity

data class ProfileResponseDto(
    val id: Long,
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
)


fun ProfileEntity.toResponseDto() = ProfileResponseDto(
    id=id!!,
    userId=user!!,
    firstName=firstName!!,
    lastName=lastName!!,
    phoneNumber=phoneNumber!!
)
