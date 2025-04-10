package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "menus")
data class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="name", nullable = false)
    val name: String = "",

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name="restaurant_id")
    val restaurant: Restaurant? = null,

    @Column(name="price", precision = 10, scale = 2, nullable = false)
    val price: BigDecimal = BigDecimal(0)
) {
    constructor():  this(null, "", null, BigDecimal(0.0))
}