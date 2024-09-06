package com.visitoron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VisitoronBackendApplication

fun main(args: Array<String>) {
    runApplication<VisitoronBackendApplication>(*args)
}
