package com.coded.ordering.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "orders_items")
class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "menu_id")
    val item: MenuEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity? = null,

    @Column(name = "quantity")
    val quantity: Int? = null
) {
    constructor(): this(null, null, null, null)
}
