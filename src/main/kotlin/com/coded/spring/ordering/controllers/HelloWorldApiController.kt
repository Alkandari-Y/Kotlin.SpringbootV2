package com.coded.spring.ordering.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/hello-world")
class HelloWorldApiController {
    @GetMapping
    fun helloWorld(): String = "Hello World!"

}