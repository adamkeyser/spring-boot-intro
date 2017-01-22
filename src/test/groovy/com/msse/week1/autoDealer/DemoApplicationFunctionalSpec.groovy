package com.msse.week1.autoDealer

import com.msse.week1.autoDealer.controller.CarController
import com.msse.week1.autoDealer.controller.DealershipController
import com.msse.week1.autoDealer.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationFunctionalSpec extends Specification {

  @Autowired
  WebApplicationContext context

  @Autowired
  TestRestTemplate testRestTemplate

  @Shared
  CarRepository carRepository = Mock(CarRepository)


  def setup() {
    context.getBean(CarController).carRespository = carRepository
    context.getBean(DealershipController).dealershipRespository = dealershipController
  }

  def 'spring context loaded'() {
    when:
    context
    then:
    context
  }

  def 'gets invitee'() {
    when:
    def result = testRestTemplate.getForObject('/cars/1', Map)


    then:
    carRepo
    result.make == "Honda"
    result.model == "Civic"
  }

  def 'searches by last name'() {
    when:
    //http://localhost:8080/invitees/search/findByLastName?lastName=keyser
    def result = testRestTemplate.getForObject('/invitees/search/findByLastName?lastName=keyser', Map)
    Map invitee = result._embedded.invitees.find()
    then:
    result._embedded.invitees.size() == 1
    invitee.firstName == "adam"
    invitee.lastName == "keyser"
  }

}
