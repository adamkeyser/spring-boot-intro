package com.msse.week1.autoDealer.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*
import java.text.NumberFormat

/*
POST /birthdayParties HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{"invitees" : ["http://localhost:8080/invitees/4"]}
 */

@Entity
class Dealership {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id

  String dealershipName

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  Set<Car> cars

  @JsonSerialize
  String inventoryValue() {
    Double value = cars.findAll { it.forSale }.price.findAll { it }.sum() ?: 0
    NumberFormat.currencyInstance.format(value)
  }

  @JsonSerialize
  Integer carsForSale() {
    return cars?.count { it.forSale } ?: 0
  }

}
