package com.coded.spring.ordering.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloWorldApiController {
    @GetMapping("/api/v1/hello-world")
    fun helloWorld(): String = "Hello World!"

}