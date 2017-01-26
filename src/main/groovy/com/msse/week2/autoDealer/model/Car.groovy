package com.msse.week2.autoDealer.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id

  String make
  String model
  Integer year
  Double price
  CarStatus carStatus

  @ManyToOne
  @JsonBackReference
  Dealership dealership

  @JsonIgnore
  boolean isForSale() {
    return CarStatus.SALE == carStatus
  }

}