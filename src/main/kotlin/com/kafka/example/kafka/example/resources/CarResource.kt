package com.kafka.example.kafka.example.resources

import com.kafka.example.kafka.example.domains.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/cars")
class CarResource {

    @Autowired
    private lateinit var carService: CarService

    @GetMapping
    fun findAll() = carService.findAll()

    @PostMapping
    fun insert(@Valid @RequestBody car: Car) = carService.insert(car)
}