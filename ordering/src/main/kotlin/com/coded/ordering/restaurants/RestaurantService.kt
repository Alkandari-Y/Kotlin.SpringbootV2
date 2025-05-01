package com.coded.ordering.restaurants

import com.coded.ordering.domain.entities.RestaurantEntity
import com.coded.ordering.domain.projections.RestaurantInfoProjection

interface RestaurantService {
    fun findAll(): List<RestaurantEntity>
    fun getInto(): List<RestaurantInfoProjection>
    fun create(restaurant: RestaurantEntity): RestaurantEntity
    fun findById(id: Long): RestaurantEntity?
    fun findByName(name: String): RestaurantEntity?
}