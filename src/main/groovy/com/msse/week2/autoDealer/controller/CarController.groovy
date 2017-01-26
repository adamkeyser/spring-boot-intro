package com.msse.week2.autoDealer.controller

import com.msse.week2.autoDealer.model.Car
import com.msse.week2.autoDealer.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
class CarController {

  @Autowired
  CarRepository carRespository

  @GetMapping()
  Collection<Car> getCars() {
    carRespository.findAll()
  }

  @GetMapping("/{id}")
  Car getCar(@PathVariable Long id) {
    carRespository.findOne(id)
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"make":"Chevy", "model":"Impala"}' http://localhost:8080/cars
  @PostMapping()
  Car addCar(@RequestBody Car car) {
    carRespository.save(car)
  }

}