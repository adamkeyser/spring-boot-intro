package com.msse.week1.birthday.repository

import com.msse.week1.birthday.model.Invitee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration
@DataJpaTest
@Unroll
class InviteeRepositorySpec extends Specification {

  @Autowired
  InviteeRepository inviteeRepository


  def 'gets first invitee from script'() {
    when:
    Invitee invitee = inviteeRepository.getOne(1L)
    then:
    invitee.firstName == "adam"
  }

  def 'can create'() {
    when:
    Invitee invitee = new Invitee(firstName: 'testFirst', lastName: 'testLast')
    inviteeRepository.save(invitee)

    then:
    Invitee invitee1 = inviteeRepository.getOne(invitee.id)
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
