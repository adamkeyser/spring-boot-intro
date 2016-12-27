package com.example

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SpringBootApplication
class DemoApplication {

  static void main(String[] args) {
    SpringApplication.run DemoApplication, args
  }

  @Bean
  CommandLineRunner onStart(InviteeRespository repository) {
    return new CommandLineRunner() {
      @Override
      void run(String... args) throws Exception {
        repository.save(new Invitee(firstName: 'adam', lastName: 'keyser'))
        repository.save(new Invitee(firstName: 'bart', lastName: 'simpson', plusOnes: 2))

        repository.findAll().each {
          println it
        }

        println repository.findByLastName('keyser')


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

//@RestController
//class InviteeRestController {

//  @Autowired
//  InviteeRespository inviteeRespository
//
//  @RequestMapping("/inviteez")
//  Collection<Invitee> invitees() {
//    inviteeRespository.findAll()
//  }

//}

//@RepositoryRestResource
public interface InviteeRespository extends JpaRepository<Invitee, Long> {
  List<Invitee> findByLastName(String lastName)
}







