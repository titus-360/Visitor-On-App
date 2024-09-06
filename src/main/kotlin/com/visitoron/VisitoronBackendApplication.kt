package com.visitoron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class VisitoronBackendApplication

fun main(args: Array<String>) {
    runApplication<VisitoronBackendApplication>(*args)
}
