package com.coded.ordering.domain.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "restaurants")
class RestaurantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="name", nullable = false)
    val name: String = "",

    @OneToMany(mappedBy = "restaurant", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JsonManagedReference
    val menus: List<MenuEntity>? = emptyList()

) {
    constructor(): this(null, "", emptyList())
}
