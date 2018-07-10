package com.kafka.example.kafka.example.domains

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Car(

    @Id
    @GeneratedValue
    var id: Long? = null,

    @field:[NotNull Size(min = 1, max = 64)]
    var name: String? = null

)