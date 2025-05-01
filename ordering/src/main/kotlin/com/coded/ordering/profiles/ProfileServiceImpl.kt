package com.coded.ordering.profiles

import com.coded.ordering.profiles.dtos.ProfileCreateRequestDto
import com.coded.ordering.domain.entities.ProfileEntity
import com.coded.ordering.profiles.dtos.toEntity
import com.coded.ordering.repositories.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository,
): ProfileService {
    override fun findAll(): List<ProfileEntity> {
        return profileRepository.findAll()
    }

    override fun createProfile(profile: ProfileCreateRequestDto, userId: Long): ProfileEntity {

        val profileExists = profileRepository.findByUser(userId)

        if (profileExists != null) {
            throw IllegalArgumentException("User already has a profile")
        }

        return profileRepository.save(profile.toEntity().copy(user = userId))
    }
}