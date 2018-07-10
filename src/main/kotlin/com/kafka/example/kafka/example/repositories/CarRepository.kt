package com.kafka.example.kafka.example.resources

import com.kafka.example.kafka.example.domains.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
}