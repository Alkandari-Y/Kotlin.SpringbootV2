package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(name="username", nullable = false, unique = true)
    val username: String = "",

    @Column(name="name", nullable = false)
    val name: String = "",

    @Column(name="email", unique = true)
    val email: String = "",

    @Column(name="password")
    val password: String = "",
) {
    constructor(): this(null, "", "",  "", "")
}
