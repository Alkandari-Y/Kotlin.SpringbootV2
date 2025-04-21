package com.coded.spring.ordering.users

import com.coded.spring.ordering.domain.entities.UserEntity
import com.coded.spring.ordering.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service



@Service
class UserServiceImpl (private val userRepository: UserRepository): UserService {
    override fun findAll(): List<UserEntity> = userRepository.findAll()
    override fun createUser(user: UserEntity): UserEntity = userRepository.save(user)
    override fun findById(id: Long): UserEntity? = userRepository.findByIdOrNull(id)
    override fun findByUserName(userName: String): UserEntity? = userRepository.findByUsername(userName)
}