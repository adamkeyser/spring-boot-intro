package com.msse.week2.autoDealer.repository

import com.msse.week2.autoDealer.model.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
@SpringBootTest
class CarRepositorySpec extends Specification {

  @Autowired
  CarRepository carRepository


  def 'gets first car from script'() {
    when:
    Car car = carRepository.findOne(1L)
    then:
    car.make == 'Honda'
    car.model == 'Civic'
  }

  def 'can create'() {
    when:
    Car car = new Car(make: 'Ford', model: 'F-150', year: 1999)
    carRepository.save(car)

    then:
    Car car1 = carRepository.getOne(car.id)
    car1.make == 'Ford'

  }

  def 'gets by last name #des'() {
    setup:
    Car car = new Car(make: 'Ford', model: 'F-150', year: 1999)
    carRepository.save(car)

    when:
    List cars = carRepository.findByModel(model)

    then:
    cars.find()?.model == expectedModel

    where:
    desc                | model   | expectedModel
    'Save and get Ford' | 'F-150' | 'F-150'
    'null check'        | null    | null
  }

}
