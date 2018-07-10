package com.kafka.example.kafka.example.configurations

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaConfig {

    @Value("\${kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Bean
    open fun producerFactory(): ProducerFactory<String, String> =
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        ).let {
            DefaultKafkaProducerFactory(it)
        }

    @Bean
    open fun kafkaTemplate(): KafkaTemplate<String, String> = KafkaTemplate(producerFactory())

}