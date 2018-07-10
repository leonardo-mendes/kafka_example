package com.kafka.example.kafka.example.resources

import com.kafka.example.kafka.example.domains.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CarService {

    @Autowired
    private lateinit var carRepository: CarRepository

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun findAll(): List<Car> = carRepository.findAll()

    @Value("\${kafka.bootstrap-topic}")
    private lateinit var topic: String

    fun insert(car: Car): Car = car.let {
        it.id = null
        carRepository.save(it)
    }.also {
        kafkaTemplate.send(topic, it.toString())
    }


}