package com.kafka.example.kafka.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaExampleApplication

fun main(args: Array<String>) {
    runApplication<KafkaExampleApplication>(*args)
}
