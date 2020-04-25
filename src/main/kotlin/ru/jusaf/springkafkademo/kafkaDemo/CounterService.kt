package ru.jusaf.springkafkademo.kafkaDemo

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class CounterService {

    private val counter = AtomicInteger(0)

    fun increment() {
        counter.incrementAndGet()
    }

    fun getResult(): Int {
        return counter.get()
    }

}