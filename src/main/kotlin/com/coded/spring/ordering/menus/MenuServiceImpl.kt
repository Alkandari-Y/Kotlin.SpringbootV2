package com.coded.spring.ordering.menus

import com.coded.spring.ordering.serverCache
import com.coded.spring.ordering.menus.dtos.MenuDetailResponse
import com.coded.spring.ordering.menus.dtos.toResponse
import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection
import com.coded.spring.ordering.repositories.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuServiceImpl(
    private val menuRepository: MenuRepository
) : MenuService {
    override fun findAll(): List<MenuDetailResponse> {
        val cachedMenuItems = menuItemsCache["menuItems"]

        return if (cachedMenuItems?.size == 0 || cachedMenuItems == null) {
            println("caching menu items")
            val menuItems = menuRepository.findAll().map { it.toResponse() }
            menuItemsCache.put("menuItems", menuItems)
            menuItems
        } else {
            println("retrieving ${cachedMenuItems.size} menu items")
            menuItemsCache["menuItems"] ?: listOf()
        }
    }

    override fun create(menuItem: MenuEntity): MenuEntity {
        val menu = menuRepository.save(menuItem)
        return menu
    }

    override fun findById(id: Long): MenuDetailResponse? = menuRepository.findByIdOrNull(id)?.toResponse()

    override fun findAllIn(items: List<Long>): List<MenuEntity> {
        return menuRepository.findAllByIdIn(items)
    }

    override fun findByRestaurantId(restaurantId: Long)
        : List<MenuBasicInfoProjection> = menuRepository.findByRestaurant_Id(restaurantId)

    override fun getMenusInRequestOrder(menuIds: List<Long>): List<MenuEntity> {
        return menuRepository.findAllByIdIn(menuIds)
    }

    override fun searchMenus(menuName: String?, restName: String?): List<MenuInfoSearchProjection> {
        return when {
            !menuName.isNullOrBlank() && !restName.isNullOrBlank() ->
                menuRepository.searchByMenuAndRestaurantName(
                    menuName=menuName,
                    restName=restName
                )
            !menuName.isNullOrBlank() -> menuRepository.findByNameContainingIgnoreCase(menuName)
            !restName.isNullOrBlank() -> menuRepository.findByRestaurantNameContainingIgnoreCase(restName)
            else -> emptyList()
        }
    }
}

val menuItemsCache = serverCache.getMap<String, List<MenuDetailResponse>>("menuItems")