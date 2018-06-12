package com.remote.banking.models.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PersonDTO implements Serializable {

    public final String firstName;

    public final String lastName;

    public final LocalDate dateOfBirth;

    public final String gender;

    public final String emailsList;

    public PersonDTO(String firstName, String lastName, LocalDate dateOfBirth, String gender, String emailsList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.emailsList = emailsList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDTO{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", emailsList=").append(emailsList);
        sb.append('}');
        return sb.toString();
    }
}
