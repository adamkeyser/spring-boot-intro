package com.msse.week1.birthday.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

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