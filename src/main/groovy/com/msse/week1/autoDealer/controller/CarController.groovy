package com.msse.week1.autoDealer.controller

import com.msse.week1.autoDealer.model.Car
import com.msse.week1.autoDealer.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CarController {

  @Autowired
  CarRepository carRespository

  @GetMapping("/cars")
  Collection<Car> getCars() {
    carRespository.findAll()
  }

  @GetMapping("/car/{id}")
  Car getCar(@PathVariable long id) {
    carRespository.findOne(id)
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"firstName":"sara", "lastName":"johnson"}' http://localhost:8080/invitee
  @PostMapping("/car")
  Car addCar(@RequestBody Car car) {
    carRespository.save(car)
  }

}