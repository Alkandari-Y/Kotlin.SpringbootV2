package com.coded.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @JoinColumn(name="user_id", unique = true)
    val user: Long? = null,

    @Column(name="first_name")
    val firstName: String? = null,

    @Column(name="last_name")
    val lastName: String? = null,

    @Column(name="phone_number")
    val phoneNumber: String? = null,

) {
    constructor(): this(null, null, "", "", "")
}