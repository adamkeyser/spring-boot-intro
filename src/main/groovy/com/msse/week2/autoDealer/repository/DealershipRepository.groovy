package com.msse.week2.autoDealer.repository

import com.msse.week2.autoDealer.model.Dealership
import org.springframework.data.jpa.repository.JpaRepository

//@RepositoryRestResource
public interface DealershipRepository extends JpaRepository<Dealership, Long> {

}
