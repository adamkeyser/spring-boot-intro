package com.msse.week1.birthday

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationFunctionalSpec extends Specification {

  @Autowired
  WebApplicationContext context

  @Autowired
  TestRestTemplate testRestTemplate


  def 'hellos the world'() {
    setup:
    Map map = [test: "test"]
    when:
    map << [test2: "test2"]
    then:
    map.test2 == "test2"
    context != null
  }

  def 'gets invitee'() {
    when:
    def result = testRestTemplate.getForObject('/invitees/1', Map)
    then:
    result.firstName == "adam"
    result.lastName == "keyser"
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
