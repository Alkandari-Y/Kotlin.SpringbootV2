package com.coded.spring.ordering.domain.projections

interface RestaurantDetail {
    val getId: Long
    val getName: String
    val getItems: Iterable<MenuItemSummary>
}