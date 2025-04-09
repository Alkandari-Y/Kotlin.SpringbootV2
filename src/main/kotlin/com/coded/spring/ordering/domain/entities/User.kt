package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="username", nullable = false, unique = true)
    val username: String = "",

    @Column(name="name", nullable = false)
    val name: String = ""
) {
    constructor(): this(null, "", "")
}
