    package com.coded.ordering.domain.entities

    import jakarta.persistence.*

    @Entity
    @Table(name = "orders")
    class OrderEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        val id: Long? = null,

        @JoinColumn(name="user_id")
        val userId: Long?,

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
        @JoinColumn(name="restaurant_id")
        val restaurant: RestaurantEntity?,


        @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val orderItems: List<OrderItemEntity>? = null
    ) {
        constructor():  this(null, null, null)

    }