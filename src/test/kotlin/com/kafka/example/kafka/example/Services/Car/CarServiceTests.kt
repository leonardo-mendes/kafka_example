package com.kafka.example.kafka.example.Services.Car

import com.kafka.example.kafka.example.KafkaExampleApplicationTests
import com.kafka.example.kafka.example.domains.Car
import com.kafka.example.kafka.example.resources.CarService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class CarServiceTests : KafkaExampleApplicationTests() {

    @Autowired
    lateinit var carService: CarService

    @Before
    fun initialize() {
        var car = Car(1, "CarExample")
        carService.insert(car)
    }

    @After
    fun finalize() {
        carService.deleteAll()
    }

    @Test
    fun `should to find a not empty list with all cars`() {
        var list = carService.findAll()
        assertTrue(!list.isEmpty())
        assertEquals(1, list.size)
    }

    @Test
    fun `should to delete all cars and return a empty list`() {
        carService.deleteAll()
        var list = carService.findAll()
        assertTrue(list.isEmpty())
    }


    @Test
    fun `should to list all cars and return a car`() {
        var list = carService.findAll()
        assertTrue(!list.isEmpty())
        assertEquals(1, list.size)
    }

    @Test
    fun `should to insert a car in database`() {
        var car = Car(1, "CarExample")
        carService.insert(car)

        var list = carService.findAll()
        assertEquals(2, list.size)
    }

    @Test(expected = Exception::class)
    fun `shouldn't to insert a car in database`() {
        var car = Car()
        carService.insert(car)
    }

    @Test
    fun `shouldn't find a unique car by Id`() {
        var car = carService.findById(2)
        assertTrue(!car.isPresent)
    }

    @Test
    fun `should to find a unique car by Id`() {
        var car = carService.findById(1).get()
        assertEquals(1L, car.id)
    }
}