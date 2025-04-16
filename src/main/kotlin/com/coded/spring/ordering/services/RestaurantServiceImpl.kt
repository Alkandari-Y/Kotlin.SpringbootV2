package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection
import com.coded.spring.ordering.repositories.RestaurantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl(private val restaurantRepository: RestaurantRepository) : RestaurantService {
    override fun findAll(): List<RestaurantEntity> = restaurantRepository.findAll()
    override fun getInto(): List<RestaurantInfoProjection> {
        return restaurantRepository.details()
    }

    override fun create(restaurant: RestaurantEntity): RestaurantEntity = restaurantRepository.save(restaurant)
    override fun findById(id: Long): RestaurantEntity? = restaurantRepository.findByIdOrNull(id)
    override fun findByName(name: String): RestaurantEntity? = restaurantRepository.findByName(name)
}