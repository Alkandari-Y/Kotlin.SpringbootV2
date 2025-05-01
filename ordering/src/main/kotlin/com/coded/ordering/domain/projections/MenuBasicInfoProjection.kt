package com.coded.ordering.domain.projections

import java.math.BigDecimal

interface MenuBasicInfoProjection {
    val name: String
    val id: Long
    val price: BigDecimal
}

interface MenuInfoSearchProjection : MenuBasicInfoProjection {
    val restaurant:RestaurantInfoProjection

    interface RestaurantInfoProjection {
        val id: Float
        val name: String
    }
}