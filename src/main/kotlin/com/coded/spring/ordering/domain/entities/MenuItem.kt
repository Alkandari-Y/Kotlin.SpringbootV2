package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "menu_items")
class MenuItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="name", nullable = false)
    val name: String = "",

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name="restaurant_id")
    val restaurant: Restaurant? = null
) {
    constructor():  this(null, "", null)
}