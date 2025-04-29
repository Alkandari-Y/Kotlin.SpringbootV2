package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.profiles.dtos.ProfileCreateRequestDto
import com.coded.spring.ordering.profiles.dtos.ProfileResponseDto
import com.coded.spring.ordering.profiles.dtos.toResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Profile API")
@RestController
@RequestMapping("/api/v1/profiles")
class ProfileApiController(
    private val profileService: ProfileService,
) {

    @Operation(summary = "Returns a list of all profiles for authenticated users")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Returns a list of all profiles",
            content = [
                Content(
                    mediaType = "application/json")
            ]),
    )
    @GetMapping
    fun getProfiles() = profileService.findAll().map { it.toResponseDto() }


    @Operation(summary = "Authenticated users can create/update a profile")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "Users can create or edits their profile",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ProfileResponseDto::class)
                )
            ]),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json",
                )
            ]),
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
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