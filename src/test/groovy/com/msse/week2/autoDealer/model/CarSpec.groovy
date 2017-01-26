package com.msse.week2.autoDealer.model

import spock.lang.Specification

class CarSpec extends Specification {

  def 'lists correctly for sale'() {
    given:
    Car car = new Car(carStatus: status)

    expect:
    car.isForSale() == expected

    where:
    status               | expected
    CarStatus.SALE       | true
    CarStatus.PROCESSING | false
    CarStatus.SOLD       | false

  }
}
