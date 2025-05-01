package com.coded.authentication.users

import com.coded.authentication.users.UserEntity

interface UserService {
    fun findAll(): List<com.coded.authentication.users.UserEntity>
    fun createUser(user: com.coded.authentication.users.UserEntity): com.coded.authentication.users.UserEntity
    fun findById(id: Long): com.coded.authentication.users.UserEntity?
    fun findByUserName(userName: String): com.coded.authentication.users.UserEntity?
}