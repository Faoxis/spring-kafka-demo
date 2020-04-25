package ru.jusaf.springkafkademo

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableAdminServer
class SpringKafkaDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringKafkaDemoApplication>(*args)
}
