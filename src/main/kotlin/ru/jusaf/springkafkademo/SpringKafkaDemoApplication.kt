package ru.jusaf.springkafkademo

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.Closeable
import java.time.Duration
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import kotlin.concurrent.thread

@SpringBootApplication
class SpringKafkaDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringKafkaDemoApplication>(*args)

    val topic = "spring-kafka-demo"
    val producer = MyProducer(topic)
    val messages = 100_000
    val launch = CountDownLatch(messages)
    val pool = Executors.newFixedThreadPool(100)
    (0..messages).forEach { i ->
//        pool.submit {
            try {
                producer.send(i.toString(), "Hello from MyProducer!")
                launch.countDown()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            TimeUnit.SECONDS.sleep(1)
        }
    }
    launch.await()

    val consumer = MyConsumer(topic)
    consumer.consume(Consumer { record ->
        println("Got key: ${record.key()}, value: ${record.value()}, " +
           "partition: ${record.partition()}")
    })

    producer.close()
    TimeUnit.HOURS.sleep(10)
    consumer.close()
}

class MyConsumer(private val topic: String): Closeable {
    private val consumer = getConsumer()

    private fun getConsumer(): KafkaConsumer<String, String> {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "kafka3:9092"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name

        val consumer = KafkaConsumer<String, String>(props)
        consumer.subscribe(listOf(topic))
        return consumer
    }

    fun consume(recordConsumer: Consumer<ConsumerRecord<String, String>>) {
        // new Thread ( lambda ).start()
        thread {
            while (true) {
                val records = consumer.poll(Duration.ofSeconds(1))
                records.forEach { record ->
                    recordConsumer.accept(record)
                }
            }
        }

    }

    override fun close() {
        consumer.close()
    }



}

class MyProducer(val topic: String): Closeable {

    private val producer = getProducer()

    private fun getProducer(): KafkaProducer<String, String> {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "kafka7:9092"
        props[ProducerConfig.CLIENT_ID_CONFIG] = "clientId"
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name

        return KafkaProducer(props)
    }

    fun send(key: String, value: String) {
        producer
          .send(ProducerRecord(topic, key, value))
          .get()
    }

    override fun close() {
        producer.close()
    }

}
