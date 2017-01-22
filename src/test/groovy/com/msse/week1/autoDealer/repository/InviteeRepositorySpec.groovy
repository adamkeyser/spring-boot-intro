package com.msse.week1.autoDealer.repository

import com.msse.week1.autoDealer.model.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
@SpringBootTest
class InviteeRepositorySpec extends Specification {

  @Autowired
  CarRepository inviteeRepository


  def 'gets first invitee from script'() {
    when:
    Car invitee = inviteeRepository.getOne(1L)
    then:
    invitee.firstName == "adam"
  }

  def 'can create'() {
    when:
    Car invitee = new Car(firstName: 'testFirst', lastName: 'testLast')
    inviteeRepository.save(invitee)

    then:
    Car invitee1 = inviteeRepository.getOne(invitee.id)
    invitee1.firstName == 'testFirst'

  }

  def 'gets by last name #des'() {
    when:
    List inviteeList = inviteeRepository.findByLastName(lastName)
    then:

    inviteeList.find()?.firstName == expectedFirstName

    where:
    desc         | lastName | expectedFirstName
    'keyser'     | 'keyser' | 'adam'
    'null check' | null     | null
  }

}
