package com.msse.week1.birthday.repository

import com.msse.week1.birthday.model.BirthdayParty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
public interface BirthdayPartyRepository extends JpaRepository<BirthdayParty, Long> {

}
