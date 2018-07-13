package com.kafka.example.kafka.example.configurations

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.annotation.KafkaListener




@Configuration
class KafkaConfig {

    @Value("\${kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Value("\${kafka.bootstrap-group")
    private lateinit var groupId: String

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

    private fun consumerFactory(): ConsumerFactory<String, String> =
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ConsumerConfig.GROUP_ID_CONFIG to groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        ).let { DefaultKafkaConsumerFactory(it) }

    @Bean
    open fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> =
        ConcurrentKafkaListenerContainerFactory<String, String>()
            .apply { consumerFactory = consumerFactory() }


    @KafkaListener(topics = ["rw_events"])
    fun listen(message: String) {
        println("Received Message from Kafka: $message")
    }
}