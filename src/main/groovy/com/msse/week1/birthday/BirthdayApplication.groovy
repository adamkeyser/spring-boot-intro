package com.msse.week1.birthday

import com.msse.week1.birthday.model.BirthdayParty
import com.msse.week1.birthday.repository.BirthdayPartyRepository
import com.msse.week1.birthday.repository.InviteeRepository
import com.msse.week1.birthday.model.Invitee
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class BirthdayApplication {

  static void main(String[] args) {
    SpringApplication.run BirthdayApplication, args
  }

  @Bean
  CommandLineRunner onStart(InviteeRepository inviteeRespository, BirthdayPartyRepository birthdayPartyRespository) {
    return new CommandLineRunner() {
      @Override
      @Transactional
      void run(String... args) throws Exception {
        Invitee adam = new Invitee(firstName: 'adam', lastName: 'keyser', attending: false)
        Invitee bart = new Invitee(firstName: 'bart', lastName: 'simpson', plusOnes: 2, attending: true)
        inviteeRespository.save(adam)
        inviteeRespository.save(bart)

        BirthdayParty party = new BirthdayParty(invitees: [adam, bart], date: new Date())
        birthdayPartyRespository.save(party)

        inviteeRespository.findAll().each {
          println it
        }

        birthdayPartyRespository.findAll().each {
          println it
        }

        println inviteeRespository.getOne(1L)
        println birthdayPartyRespository.getOne(1L)

        println inviteeRespository.findByLastName('keyser')
      }
    }
  }

}



//@RestController
//class InviteeRestController {
//
//  @Autowired
//  InviteeRespository inviteeRespository
//
//  @RequestMapping("/inviteez")
//  Collection<Invitee> invitees() {
//    inviteeRespository.findAll()
//  }
//
//}
//
//@RestController
//class BirthdayPartyController {
//
//  @Autowired
//  BirthdayPartyRespository birthdayPartyRespository
//
//  @RequestMapping("/birthdayParty")
//  Collection<BirthdayParty> birthdayParties() {
//    birthdayPartyRespository.findAll()
//  }
//
////  @RequestMapping("/birthdayParty/{id}")
////  BirthdayParty birthdayPartyAttendee(@PathParam int id) {
////    birthdayPartyRespository.getOne(id).toString()
////  }
//
//
//
//}








