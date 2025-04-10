package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "restaurants")
class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="name", nullable = false)
    val name: String = "",

) {
    constructor(): this(null, "")
}
