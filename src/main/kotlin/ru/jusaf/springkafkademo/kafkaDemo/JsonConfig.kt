package ru.jusaf.springkafkademo.kafkaDemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

val mapper: ObjectMapper = ObjectMapper().registerModules(KotlinModule(), Jdk8Module(), JavaTimeModule())

class DemoSerializer: JsonSerializer<Any>(mapper)
class DemoDeserializer: JsonDeserializer<Any>(mapper)
