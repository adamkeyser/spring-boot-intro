package com.msse.week2.autoDealer.repository

import com.msse.week2.autoDealer.model.Car
import org.springframework.data.jpa.repository.JpaRepository

public interface CarRepository extends JpaRepository<Car, Long> {

}

