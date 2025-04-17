package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.requests.ProfileCreateRequestDto
import com.coded.spring.ordering.domain.requests.toEntity
import com.coded.spring.ordering.domain.entities.ProfileEntity
import com.coded.spring.ordering.repositories.ProfileRepository
import com.coded.spring.ordering.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository
): ProfileService {
    override fun findAll(): List<ProfileEntity> {
        return profileRepository.findAll()
    }

    override fun createProfile(profile: ProfileCreateRequestDto): ProfileEntity {
        val user = userRepository.findByIdOrNull(profile.userId)
            ?: throw IllegalArgumentException("User with id ${profile.userId} doesn't exist")

        val profileExists = profileRepository.findByUserId(profile.userId)

        if (profileExists != null) {
            throw IllegalArgumentException("User already has a profile")
        }

        return profileRepository.save(profile.toEntity().copy(user = user))
    }
}