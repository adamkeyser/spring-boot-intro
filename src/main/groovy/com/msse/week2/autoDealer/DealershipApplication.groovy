package com.msse.week2.autoDealer

import com.msse.week2.autoDealer.model.Car
import com.msse.week2.autoDealer.model.Dealership
import com.msse.week2.autoDealer.repository.CarRepository
import com.msse.week2.autoDealer.repository.DealershipRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class DealershipApplication {

  static void main(String[] args) {
    SpringApplication.run DealershipApplication, args
  }

  @Bean
  CommandLineRunner onStart(CarRepository carRepository, DealershipRepository dealershipRepository) {
    return new CommandLineRunner() {
      @Override
      @Transactional
      void run(String... args) throws Exception {
        Dealership dealership = new Dealership(dealershipName: "Fred's Cars")
        Car honda1 = new Car(make: 'Honda', model: 'Civic', year: 2000, price: 1000.3030, dealership: dealership)
        Car toyota1 = new Car(make: 'Toyota', model: 'Corolla', year: 2015, price: 2500.999, dealership: dealership)

        dealership.cars = [honda1, toyota1]
        dealershipRepository.save(dealership)

        carRepository.findAll().each {
          println it
        }

        dealershipRepository.findAll().each {
          println it
        }

        println carRepository.getOne(1L)
        println dealershipRepository.getOne(1L)

      }
    }
  }
}











