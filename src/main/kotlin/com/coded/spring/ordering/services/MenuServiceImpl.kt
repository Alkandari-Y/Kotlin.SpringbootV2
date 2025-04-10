package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.repositories.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuServiceImpl(private val menuRepository: MenuRepository) : MenuService {
    override fun findAll(): List<Menu> = menuRepository.findAll()
    override fun create(menuItem: Menu): Menu = menuRepository.save(menuItem)
    override fun findById(id: Long): Menu? = menuRepository.findByIdOrNull(id)
    override fun findAllIn(items: List<Long>): List<Menu> {
        return menuRepository.findAllByIdIn(items)
    }

    override fun findByRestaurantId(restaurantId: Long)
        : List<MenuBasicInfoProjection> = menuRepository.findByRestaurant_Id(restaurantId)

    override fun getMenusInRequestOrder(menuIds: List<Long>): List<Menu> {
        return menuRepository.findAllByIdIn(menuIds)
    }
}