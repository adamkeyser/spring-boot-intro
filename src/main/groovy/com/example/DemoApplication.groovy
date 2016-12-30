package com.example

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.property.access.spi.Getter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.websocket.server.PathParam

@SpringBootApplication
class DemoApplication {

  static void main(String[] args) {
    SpringApplication.run DemoApplication, args
  }

  @Bean
  CommandLineRunner onStart(InviteeRespository inviteeRespository, BirthdayPartyRespository birthdayPartyRespository) {
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

@Entity
class Invitee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id
  String firstName
  String lastName
  boolean attending
  int plusOnes

  @Override
  public String toString() {
    return "Invitee{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", plusOnes=" + plusOnes +
        '}';
  }
}


/*
POST /birthdayParties HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{"invitees" : ["http://localhost:8080/invitees/4"]}
 */
@Entity
@Table(name="birthday_party")
class BirthdayParty {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id

  @OneToMany(fetch = FetchType.LAZY)
  Set<Invitee> invitees

  Date date

  @JsonSerialize
  public int attendingCount() {
    if (!invitees) return 0
    int attending = invitees?.collectMany { Invitee invitee ->
       return [invitee.attending ? 1 + invitee.plusOnes : 0]
    }?.sum()
    return attending
  }


  @Override
  public String toString() {
    return "BirthdayParty{" +
        "id=" + id +
        ", invitees=" + invitees +
        ", attendingCount=" + attendingCount() +
        ", date=" + date +
        '}';
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

@RepositoryRestResource
public interface InviteeRespository extends JpaRepository<Invitee, Long> {

  //http://localhost:8080/invitees/search/findByLastName?lastName=keyser
  List<Invitee> findByLastName(@Param("lastName") String lastName)
}

@RepositoryRestResource
public interface BirthdayPartyRespository extends JpaRepository<BirthdayParty, Long> {

}







