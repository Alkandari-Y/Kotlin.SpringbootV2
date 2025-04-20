package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.requests.ProfileCreateRequestDto
import com.coded.spring.ordering.domain.dtos.toResponseDto
import com.coded.spring.ordering.services.ProfileService
import jakarta.validation.Valid
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/profiles")
class ProfileApiController(
    private val profileService: ProfileService,
) {

    @GetMapping
    fun getProfiles() = profileService.findAll().map { it.toResponseDto() }

    @PostMapping
    fun createProfile(
        @Valid @RequestBody profileCreateDto: ProfileCreateRequestDto,
        authentication: Authentication,
    )
    : ResponseEntity<Any> {
        return try {
            val userDetails = authentication.principal as UserDetails
            val profile = profileService.createProfile(profileCreateDto, userDetails)
            ResponseEntity(profile.toResponseDto(), HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}