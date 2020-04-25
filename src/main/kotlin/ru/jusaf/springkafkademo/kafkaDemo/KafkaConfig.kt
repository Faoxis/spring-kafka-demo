package ru.jusaf.springkafkademo.kafkaDemo

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaConfig {

    @Bean
    fun producerFactory(properties: KafkaProperties): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(properties.buildProducerProperties())
    }

    @Bean
    fun kafkaTemplate(factory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate(factory)
    }

}