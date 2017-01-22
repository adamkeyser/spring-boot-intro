package com.msse.week1.birthday.repository

import com.msse.week1.birthday.model.Dealership
import org.springframework.data.jpa.repository.JpaRepository

//@RepositoryRestResource
public interface DealershipRepository extends JpaRepository<Dealership, Long> {

}
