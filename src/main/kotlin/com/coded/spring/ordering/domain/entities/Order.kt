package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name="user_id")
    val user: User?,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name="restaurant_id")
    val restaurant: Restaurant?,


    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val orderItems: List<OrderItem>? = null
) {
    constructor():  this(null, null, null)

}