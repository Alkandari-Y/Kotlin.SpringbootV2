package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}