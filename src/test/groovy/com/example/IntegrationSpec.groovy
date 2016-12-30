package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
class IntegrationSpec extends Specification {

  @Autowired
  InviteeRespository inviteeRespository


  def 'hellos the world'() {
    when:
    Invitee invitee = inviteeRespository.getOne(1L)
    then:
    invitee.firstName == "adam"
  }

}
