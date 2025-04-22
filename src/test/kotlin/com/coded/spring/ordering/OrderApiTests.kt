package com.coded.spring.ordering

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.orders.dtos.OrderCreateRequestDto
import com.coded.spring.ordering.orders.dtos.OrderCreateResponse
import com.coded.spring.ordering.orders.dtos.OrderItemCreateRequestDto
import com.coded.spring.ordering.repositories.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.security.crypto.password.PasswordEncoder
import java.math.BigDecimal
import kotlin.test.assertEquals

const val BASE_ORDER_API = "/api/v1/orders"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApiTests
@Autowired constructor(
    var testRestTemplate: TestRestTemplate
) {
    var loginToken: String? = null

    @BeforeEach
    fun `login user`() {
        val response = testRestTemplate.postForEntity(
            AUTH_LOGIN_API,
            USER_LOGIN_CREDENTIALS,
            JwtResponseDto::class.java,
        )
        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        loginToken = response.body?.token
    }

    @Test
    fun `test create order returned and HTTP Status 200`() {
        val expectedOrder = OrderCreateRequestDto(
            restaurantId = 1,
            items = listOf(
                OrderItemCreateRequestDto(
                    itemId = 1,
                    quantity = 2
                ),
                OrderItemCreateRequestDto(
                    itemId = 2,
                    quantity = 3
                ),
                OrderItemCreateRequestDto(
                    itemId = 3,
                    quantity = 1
                )
            )
        )
        val httpEntity = createHttpRequest(loginToken as String, expectedOrder)

        val orderPostResponse = testRestTemplate.exchange(
            BASE_ORDER_API,
            HttpMethod.POST,
            httpEntity,
            OrderCreateResponse::class.java
        )

        val newOrder = orderPostResponse.body
        val itemsCount = newOrder?.items?.size
        assertNotNull(itemsCount)
        assertNotNull(orderPostResponse.body)
        assertEquals(200, orderPostResponse.statusCode.value())

    }


    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(
            @Autowired userRepository: UserRepository,
            @Autowired restaurantsRepository: RestaurantRepository,
            @Autowired menuRepository: MenuRepository,
            @Autowired passwordEncoder: PasswordEncoder
        ) {
            userRepository.createUser(passwordEncoder)
            val restaurants = restaurantsRepository.saveAll(
                listOf(
                    RestaurantEntity(
                        name="Five guys"
                    ),
                    RestaurantEntity(
                        name="pick"
                    )
                )
            )

            menuRepository.saveAll(
                listOf(
                    MenuEntity(
                        restaurant = restaurants[0],
                        name = "Cheese Burger",
                        price = BigDecimal(4.00)
                    ),
                    MenuEntity(
                        restaurant = restaurants[0],
                        name = "Milkshake",
                        price = BigDecimal(2.50)
                    ),
                    MenuEntity(
                        restaurant = restaurants[0],
                        name = "Fries",
                        price = BigDecimal(2.00)
                    ),
                    MenuEntity(
                        restaurant = restaurants[1],
                        name = "Pasta",
                        price = BigDecimal(3.00)
                    ),
                    MenuEntity(
                        restaurant = restaurants[1],
                        name = "Latte",
                        price = BigDecimal(1.5)
                    ),
                    MenuEntity(
                        restaurant = restaurants[1],
                        name = "Frozen yogurt",
                        price = BigDecimal(3.50)
                    )
                )
            )

        }

        @JvmStatic
        @AfterAll
        fun tearDown(
            @Autowired userRepository: UserRepository,
            @Autowired orderRepository: OrderRepository,
            @Autowired restaurantsRepository: RestaurantRepository,
            @Autowired menuRepository: MenuRepository,
            @Autowired orderItemRepository: OrderItemRepository
        ) {
            orderRepository.deleteAll()
            orderItemRepository.deleteAll()
            menuRepository.deleteAll()
            restaurantsRepository.deleteAll()
            userRepository.deleteAll()
        }
    }
}
