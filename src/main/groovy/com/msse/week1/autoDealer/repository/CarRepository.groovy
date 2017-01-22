package com.msse.week1.autoDealer.repository

import com.msse.week1.autoDealer.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

//@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {

    //http://localhost:8080/invitees/search/findByLastName?lastName=keyser
    List<Car> findByModel(@Param("model") String model)
}

