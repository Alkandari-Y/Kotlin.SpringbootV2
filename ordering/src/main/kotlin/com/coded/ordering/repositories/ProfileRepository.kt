package com.coded.ordering.repositories

import com.coded.ordering.domain.entities.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository: JpaRepository<ProfileEntity, Long> {
    fun findByUser(userId: Long): ProfileEntity?
}