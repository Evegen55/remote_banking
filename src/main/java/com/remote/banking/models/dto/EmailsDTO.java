package com.remote.banking.models.dto;

public class EmailsDTO {

    public final String email;

    public final String googleAccountsIdgoogleAccounts;

    public final String personIdperson;

    public EmailsDTO(String email, String googleAccountsIdgoogleAccounts, String personIdperson) {
        this.email = email;
        this.googleAccountsIdgoogleAccounts = googleAccountsIdgoogleAccounts;
        this.personIdperson = personIdperson;
    }
}
