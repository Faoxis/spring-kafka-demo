package ru.jusaf.springkafkademo.kafkaDemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaConfig {

//    val mapper: ObjectMapper = ObjectMapper().registerModules(KotlinModule(), Jdk8Module(), JavaTimeModule())
//
//    @Bean
//    fun producerFactory(properties: KafkaProperties): ProducerFactory<String, Any> {
//        return DefaultKafkaProducerFactory(
//            properties.buildProducerProperties(),
//            StringSerializer(),
//            JsonSerializer(mapper)
//        )
//    }
//
//    @Bean
//    fun kafkaTemplate(factory: ProducerFactory<String, Any>): KafkaTemplate<String, Any> {
//        return KafkaTemplate(factory)
//    }
//
//    @Bean
//    fun consumerFactory(properties: KafkaProperties): ConsumerFactory<String, Any> {
//        val deserializer = JsonDeserializer<Any>(mapper)
//        deserializer.addTrustedPackages(User::class.java.packageName)
//        return DefaultKafkaConsumerFactory(
//            properties.buildConsumerProperties(),
//            StringDeserializer(),
//            deserializer
//        )
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, Any>): ConcurrentKafkaListenerContainerFactory<String, Any> {
//        val containerFactory = ConcurrentKafkaListenerContainerFactory<String, Any>()
//        containerFactory.consumerFactory = consumerFactory
//        return containerFactory
//    }

}