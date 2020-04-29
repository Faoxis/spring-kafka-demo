package ru.jusaf.springkafkademo.kafkaDemo

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DemoController(
    private val template: KafkaTemplate<String, Any>,
    private val counterService: CounterService
) {

    private val userTopic = "spring-kafka-demo"
    private val Topic = "spring-kafka-demo"

    @GetMapping("/send-random-user-message")
    fun sendRandomMessage(): String {
        template.send(userTopic, UUID.randomUUID().toString(), User("Petya", 42))
        return "OK"
    }


    @GetMapping("/counter-result")
    fun getCounterResult() = counterService.getResult()

}