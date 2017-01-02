package com.msse.week1.birthday.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

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