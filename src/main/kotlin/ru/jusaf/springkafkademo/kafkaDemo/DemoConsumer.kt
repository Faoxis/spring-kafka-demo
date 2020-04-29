package ru.jusaf.springkafkademo.kafkaDemo

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@Component
class DemoConsumer(
    private val counterService: CounterService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["spring-kafka-demo"], concurrency = "10")
    fun listenTopic(record: ConsumerRecord<String, User>) {
        logger.info("${record.value()}")
        counterService.increment()
    }

}
