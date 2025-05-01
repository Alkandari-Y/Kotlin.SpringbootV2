package com.coded.authentication.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Missing or malformed Authorization header")
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.removePrefix("Bearer ").trim()
        logger.info("Received JWT: $token")

        try {
            val username = jwtService.extractUsername(token)
            logger.info("Extracted username from token: $username")

            if (SecurityContextHolder.getContext().authentication == null) {
                if (jwtService.isTokenValid(token, username)) {
                    logger.info("Token is valid for username: $username")

                    val userDetails = userDetailsService.loadUserByUsername(username)
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                    logger.info("SecurityContext set for user: $username")
                } else {
                    logger.warn("Token is NOT valid for username: $username")
                }
            }
        } catch (e: Exception) {
            logger.error("JWT validation failed: ${e.message}", e)
        }

        filterChain.doFilter(request, response)
    }

}