package com.coded.authentication.users

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (private val userRepository: com.coded.authentication.users.UserRepository):
    com.coded.authentication.users.UserService {
    override fun findAll(): List<com.coded.authentication.users.UserEntity> = userRepository.findAll()
    override fun createUser(user: com.coded.authentication.users.UserEntity): com.coded.authentication.users.UserEntity = userRepository.save(user)
    override fun findById(id: Long): com.coded.authentication.users.UserEntity? = userRepository.findByIdOrNull(id)
    override fun findByUserName(userName: String): com.coded.authentication.users.UserEntity? = userRepository.findByUsername(userName)
}