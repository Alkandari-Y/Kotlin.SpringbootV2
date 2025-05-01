package com.coded.ordering.helloWorld

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Hello world")
@RestController
class HelloWorldApiController {

    @Operation(summary = "Test endpoint that requires JWT token")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Generic hello world endpoint for authenticated users",
            content = [
                Content(
                    schema = Schema(implementation = String::class),
                    mediaType = "application/json"
                )
            ]
        ),
        ApiResponse(
            responseCode = "401",
            description = "Forbidden",
            content = [
                Content(mediaType = "application/json")
            ]
        ),
    )
    @GetMapping("/api/v1/hello-world")
    fun helloWorld(
        @RequestAttribute("userId") userId: Long): String = "Hello World! $userId"


    @Operation(summary = "Test endpoint that requires JWT token")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Generic hello world endpoint for authenticated users",
            content = [
                Content(
                    schema = Schema(implementation = String::class),
                    mediaType = "application/json"
                )
            ]
        ),
        ApiResponse(
            responseCode = "401",
            description = "Forbidden",
            content = [
                Content(mediaType = "application/json")
            ]
        ),
    )
    @GetMapping("/api/v1/welcome")
    fun welcomeMessage(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Welcome to Online Ordering")

        System.getenv("companyName")?.let {
            stringBuilder.append(" by $it!")
        }

        val festivity = System.getenv("festivity")
        return when {
            !festivity.isNullOrBlank() -> festivity
            else -> stringBuilder.toString()
        }
    }
}