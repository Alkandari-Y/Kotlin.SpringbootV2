import com.coded.spring.ordering.domain.entities.UserEntity
import com.coded.spring.ordering.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class InitUserRunner {
    @Bean
    fun initUsers(userRepository: UserRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = UserEntity(
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