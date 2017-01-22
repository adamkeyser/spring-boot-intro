package com.msse.week1.autoDealer.controller

import com.msse.week1.autoDealer.model.Dealership
import com.msse.week1.autoDealer.repository.DealershipRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DealershipController {

  @Autowired
  DealershipRepository dealershipRespository

  @GetMapping("/dealerships")
  Collection<Dealership> getDealerships() {
    dealershipRespository.findAll()
  }

  @GetMapping("/dealership/{id}")
  Dealership getDealership(@PathVariable long id) {
    dealershipRespository.findOne(id)
  }


}