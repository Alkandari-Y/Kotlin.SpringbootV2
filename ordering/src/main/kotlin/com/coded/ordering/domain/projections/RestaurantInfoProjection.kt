package com.coded.ordering.domain.projections

interface RestaurantInfoProjection {
    val id: Float
    val name: String
    val menus: List<MenuBasicInfoProjection>
}