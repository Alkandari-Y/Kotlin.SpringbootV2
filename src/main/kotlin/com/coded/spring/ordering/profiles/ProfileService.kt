package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.profiles.dtos.ProfileCreateRequestDto
import com.coded.spring.ordering.domain.entities.ProfileEntity
import org.springframework.security.core.userdetails.UserDetails

interface ProfileService {
    fun findAll(): List<ProfileEntity>
    fun createProfile(profile: ProfileCreateRequestDto, userDetails: UserDetails): ProfileEntity
}