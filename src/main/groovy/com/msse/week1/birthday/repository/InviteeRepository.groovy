package com.msse.week1.birthday.repository

import com.msse.week1.birthday.model.Invitee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
public interface InviteeRepository extends JpaRepository<Invitee, Long> {

    //http://localhost:8080/invitees/search/findByLastName?lastName=keyser
    List<Invitee> findByLastName(@Param("lastName") String lastName)
}

