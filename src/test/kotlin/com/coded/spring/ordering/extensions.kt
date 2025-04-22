package com.coded.spring.ordering

import com.coded.spring.ordering.domain.entities.UserEntity
import com.coded.spring.ordering.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder

fun UserRepository.createUser(passwordEncoder: PasswordEncoder?) {
    this.save(
        UserEntity(
            name = "admin user",
            username = "admin",
            password = passwordEncoder?.encode("passwordTest123") ?: "passwordTest123",
            email = "admin@example.com",
        )
    )
}
