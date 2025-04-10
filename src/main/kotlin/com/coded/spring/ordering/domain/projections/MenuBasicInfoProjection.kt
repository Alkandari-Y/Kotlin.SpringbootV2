package com.coded.spring.ordering.domain.projections

import java.math.BigDecimal

interface MenuBasicInfoProjection {
    val name: String
    val id: Long
    val price: BigDecimal
}