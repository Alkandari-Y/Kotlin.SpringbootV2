package com.coded.ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance

@SpringBootApplication
class OrderingApplication

fun main(args: Array<String>) {
    runApplication<OrderingApplication>(*args)
    menuItemsConfig.getMapConfig("menuItems").setTimeToLiveSeconds(5)

}

val menuItemsConfig = Config("menuItems")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(menuItemsConfig)
