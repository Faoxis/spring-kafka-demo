package ru.jusaf.springkafkademo.kafkaDemo

import java.time.LocalDateTime

data class User(
    val firstName: String,
    val age: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
)