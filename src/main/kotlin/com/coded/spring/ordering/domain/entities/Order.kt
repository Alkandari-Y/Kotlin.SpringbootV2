package com.coded.spring.ordering.domain.entities

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository: JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name="username", nullable = false)
    val user: String,
    val restaurant: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    val items: List<OrderItem>
) {
    constructor(): this(null, "", "", emptyList())
}
