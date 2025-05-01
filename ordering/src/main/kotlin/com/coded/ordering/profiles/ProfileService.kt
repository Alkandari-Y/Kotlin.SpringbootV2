package com.coded.ordering.profiles

import com.coded.ordering.profiles.dtos.ProfileCreateRequestDto
import com.coded.ordering.domain.entities.ProfileEntity

interface ProfileService {
    fun findAll(): List<ProfileEntity>
    fun createProfile(profile: ProfileCreateRequestDto, userId: Long): ProfileEntity
}