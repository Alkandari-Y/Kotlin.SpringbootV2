package com.coded.authentication.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<com.coded.authentication.users.UserEntity, Long> {
    fun findByUsername(username: String): com.coded.authentication.users.UserEntity?
}