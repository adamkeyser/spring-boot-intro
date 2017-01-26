package com.msse.week2.autoDealer.controller

import com.msse.week2.autoDealer.model.Car
import com.msse.week2.autoDealer.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerSpec extends Specification {

  @Autowired
  WebApplicationContext context

  @Autowired
  TestRestTemplate testRestTemplate

  def 'spring context loaded'() {
    when:
    context
    then:
    context
  }

  def 'gets a car'() {
    setup:
    CarRepository carRepository = Mock(CarRepository)
    context.getBean(CarController).carRespository = carRepository

    when:
    def result = testRestTemplate.getForObject('/cars/1', Map)

    then:
    1 * carRepository.findOne(1) >> new Car(make: 'Honda', model: 'Civic')

    then:
    result.make == "Honda"
    result.model == "Civic"
  }
}
