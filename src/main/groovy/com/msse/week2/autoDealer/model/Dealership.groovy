package com.msse.week2.autoDealer.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*
import java.text.NumberFormat

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
