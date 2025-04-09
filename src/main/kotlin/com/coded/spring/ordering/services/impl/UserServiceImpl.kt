package com.coded.spring.ordering.services.impl

import org.springframework.stereotype.Service
import com.coded.spring.ordering.domain.entities.User
import com.coded.spring.ordering.respositories.UserRepository
import com.coded.spring.ordering.services.UserService
import org.springframework.data.repository.findByIdOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun createUser(user: User): User {
        return userRepository.save(user)
    }

    override fun getUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    override fun getUserByNameOrNull(name: String): User? {
        return userRepository.findByUsername(name)
    }

}