package com.msse.week1.birthday.model

import spock.lang.Specification


class BirthdayPartySpec extends Specification {

  def 'attendee count'() {
    setup:
    BirthdayParty party = new BirthdayParty()
    party.invitees = [new Invitee(attending: attendA, plusOnes: plusOnesA),
                      new Invitee(attending: attendB, plusOnes: plusOnesB)]

    when:
    Integer result = party.attendingCount()

    then:
    result == expectedResult

    where:
    attendA | attendB | plusOnesA | plusOnesB | expectedResult
    true    | false   | 0         | 100       | 1
    true    | true    | 0         | 0         | 2
    true    | true    | 2         | 2         | 6
  }

}
