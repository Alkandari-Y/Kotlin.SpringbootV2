package com.coded.spring.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "orders_items")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "menu_item_id")
    val menuItem: MenuItem? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order? = null,

    @Column(name = "quantity")
    val quantity: Int? = null
) {
    constructor(): this(null, null, null, null)
}
