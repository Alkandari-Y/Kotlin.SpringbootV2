package com.coded.spring.ordering.services.impl

import com.coded.spring.ordering.domain.projections.RestaurantSummary
import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.respositories.RestaurantRepository
import com.coded.spring.ordering.services.RestaurantService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl (
    private val restaurantRepository: RestaurantRepository
): RestaurantService {
    override fun save(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }

    override fun allSummaries(): Iterable<RestaurantSummary> {
        return restaurantRepository.all()
    }

    override fun getById(id: Long): Restaurant? {
        return restaurantRepository.findByIdOrNull(id)
    }

    override fun getByName(name: String): Restaurant? {
        return restaurantRepository.findByName(name= name)
    }

    override fun create(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }


}