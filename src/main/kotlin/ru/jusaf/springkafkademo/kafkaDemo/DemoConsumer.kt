package ru.jusaf.springkafkademo.kafkaDemo

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoConsumer(
    private val counterService: CounterService
) {

    @KafkaListener(topics = ["spring-kafka-demo"], concurrency = "10")
    fun listTopic(record: ConsumerRecord<String, String>) {
        counterService.increment()
    }


}