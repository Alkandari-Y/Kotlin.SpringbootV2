package com.coded.spring.ordering.respositories

import com.coded.spring.ordering.domain.entities.MenuItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemRepository: JpaRepository<MenuItem, Long?> {
    fun findByName(name: String): MenuItem?
}