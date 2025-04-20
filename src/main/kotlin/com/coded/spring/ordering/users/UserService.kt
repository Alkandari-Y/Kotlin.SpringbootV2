package com.coded.spring.ordering.users

import com.coded.spring.ordering.domain.entities.UserEntity


interface UserService {
    fun findAll(): List<UserEntity>
    fun createUser(user: UserEntity): UserEntity
    fun findById(id: Long): UserEntity?
    fun findByUserName(userName: String): UserEntity?
}