package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.User
import com.coded.spring.ordering.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserServiceImpl (private val userRepository: UserRepository): UserService {
    override fun findAll(): List<User> = userRepository.findAll()
    override fun createUser(user: User): User = userRepository.save(user)
    override fun findById(id: Long): User? = userRepository.findByIdOrNull(id)
}