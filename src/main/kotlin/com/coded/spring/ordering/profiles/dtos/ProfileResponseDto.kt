package com.coded.spring.ordering.profiles.dtos

import com.coded.spring.ordering.domain.entities.ProfileEntity

data class ProfileResponseDto(
    val id: Long,
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
)


fun ProfileEntity.toResponseDto() = ProfileResponseDto(
    id=id!!,
    userId=user?.id!!,
    firstName=firstName!!,
    lastName=lastName!!,
    phoneNumber=phoneNumber!!
)
