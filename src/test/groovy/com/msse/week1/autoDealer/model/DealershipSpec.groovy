package com.msse.week1.autoDealer.model

import spock.lang.Specification

class DealershipSpec extends Specification {

  Dealership dealership

  def setup() {
    dealership = new Dealership()
  }

  def 'inventory value returns proper amount'() {
    setup:
    dealership.cars = cars

    when:
    String result = dealership.inventoryValue()
    then:
    result == expectedResult

    where:
    description              | cars                                                                                                 | expectedResult
    'has no cars'            | []                                                                                                   | '$0.00'
    'has null cars'          | null                                                                                                 | '$0.00'
    'has one car processing' | [new Car(carStatus: CarStatus.PROCESSING, price: 5435)]                                              | '$0.00'
    'has one car'            | [new Car(carStatus: CarStatus.SALE, price: 100.50)]                                                  | '$100.50'
    'has two cars'           | [new Car(carStatus: CarStatus.SALE, price: 50000), new Car(carStatus: CarStatus.SALE, price: 20000)] | '$70,000.00'
  }

  def 'inventory size returns proper size'() {
    setup:
    dealership.cars = cars

    when:
    def result = dealership.carsForSale()

    then:
    result == expectedResult

    where:
    description             | cars                                                                                                               | expectedResult
    'has no cars'           | []                                                                                                                 | 0
    'has null cars'         | null                                                                                                               | 0
    'has 1 car for sale'    | [new Car(carStatus: CarStatus.SALE)]                                                                               | 1
    'has 3 cars 2 for sale' | [new Car(carStatus: CarStatus.SALE), new Car(carStatus: CarStatus.SALE), new Car(carStatus: CarStatus.PROCESSING)] | 2

  }

}
