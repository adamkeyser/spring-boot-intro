package com.msse.week1.birthday.repository

import com.msse.week1.birthday.repository.InviteeRepository
import com.msse.week1.birthday.model.Invitee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
class IntegrationSpec extends Specification {

  @Autowired
  InviteeRepository inviteeRepository


  def 'hellos the world'() {
    when:
    Invitee invitee = inviteeRepository.getOne(1L)
    then:
    invitee.firstName == "adam"
  }

}
