package com.kafka.example.kafka.example.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService: Thread() {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private var message: String = ""

    @Value("\${kafka.bootstrap-topic}")
    private lateinit var topic: String

    fun getMessage(message: String){
        this.message = message
    }

    override fun run() {
        println("New Thread created!")
        kafkaTemplate.send(topic, message)
    }

}
