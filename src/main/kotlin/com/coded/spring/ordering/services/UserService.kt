package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.User

interface UserService {
    fun createUser(name: User): User
    fun getUsers(): Iterable<User>
    fun getUserById(id: Long): User?
    fun getUserByNameOrNull(name: String): User?
}