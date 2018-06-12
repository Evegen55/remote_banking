package com.remote.banking.models.dto;

import java.io.Serializable;

public class EmailsDTO implements Serializable {

    public final String email;

    public final String googleAccountsIdgoogleAccounts;

    public final String personIdperson;

    public EmailsDTO(String email, String googleAccountsIdgoogleAccounts, String personIdperson) {
        this.email = email;
        this.googleAccountsIdgoogleAccounts = googleAccountsIdgoogleAccounts;
        this.personIdperson = personIdperson;
    }
}
