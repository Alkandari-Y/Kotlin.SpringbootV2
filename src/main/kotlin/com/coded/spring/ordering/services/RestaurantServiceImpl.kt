package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection
import com.coded.spring.ordering.repositories.RestaurantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl(private val restaurantRepository: RestaurantRepository) : RestaurantService {
    override fun findAll(): List<Restaurant> = restaurantRepository.findAll()
    override fun getInto(): List<RestaurantInfoProjection> {
        return restaurantRepository.details()
    }

    override fun create(restaurant: Restaurant): Restaurant = restaurantRepository.save(restaurant)
    override fun findById(id: Long): Restaurant? = restaurantRepository.findByIdOrNull(id)
    override fun findByName(name: String): Restaurant? = restaurantRepository.findByName(name)
}