package com.coded.spring.ordering.test

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders

fun <T> createHttpRequest(token: String, requestBody: T? = null): HttpEntity<T> {
    val headers = HttpHeaders()
    headers.set("Authorization", "Bearer $token")

    if (requestBody != null) {
        headers.set("Content-Type", "application/json")
    }

    return HttpEntity(requestBody, headers)
}
