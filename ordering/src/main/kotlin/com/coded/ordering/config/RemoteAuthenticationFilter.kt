package com.coded.ordering.config

import com.coded.ordering.providers.JwtAuthProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RemoteAuthenticationFilter(
    private val jwtAuthProvider: JwtAuthProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearerToken: String? = request.getHeader("Authorization")
        logger.info("Token received in filter = $bearerToken")

        if (bearerToken.isNullOrBlank() || !bearerToken.startsWith("Bearer ")) {
            logger.warn("Missing or malformed Authorization header.")
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization header is missing or invalid")
            return
        }

        try {
            val token = bearerToken.substring(7)
            val result = jwtAuthProvider.authenticateToken(token)
            logger.info("userId: ${result.userId}")

            request.setAttribute("userId", result.userId)

            val authentication = UsernamePasswordAuthenticationToken(
                result.userId,
                null,
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
            SecurityContextHolder.getContext().authentication = authentication

            logger.info("Authentication set in SecurityContext for userId = ${result.userId}")
            filterChain.doFilter(request, response)

        } catch (ex: Exception) {
            logger.error("Token validation failed", ex)
            response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid token")
        }
    }
}
