package com.coded.authentication

import com.coded.authentication.users.UserEntity
import com.coded.authentication.users.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class InitUserRunner {
    @Bean
    fun initUsers(userRepository: com.coded.authentication.users.UserRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = com.coded.authentication.users.UserEntity(
            name = "admin user",
            username = "adminUser",
            password = passwordEncoder.encode("password123"),
            email = "adminUser@ordering.com"
        )
        if (userRepository.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            userRepository.save(user)
        } else  {
            println("User ${user.username} already exists")
        }
    }
}

//fun main(args: Array<String>) {
//    runApplication<Application>(*args).close()
//}

// COMMENT to avoid multiple main function reference during compilation