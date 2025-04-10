package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.User


interface UserService {
    fun findAll(): List<User>
    fun createUser(user: User): User
    fun findById(id: Long): User?
}