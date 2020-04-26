package ru.jusaf.springkafkademo.kafkaDemo

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@Component
class DemoConsumer(
    private val counterService: CounterService
) {

    @KafkaListener(topics = ["spring-kafka-demo"], concurrency = "10")
    fun listenTopic(record: ConsumerRecord<String, String>) {
        counterService.increment()
    }


}