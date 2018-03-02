package com.remote.banking.models.for_rdbms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Evgenii_Lartcev
 */
@Entity
@Table(name = "emails")
@NamedQueries({
        @NamedQuery(name = "Emails.findAll", query = "SELECT e FROM Emails e"),
        @NamedQuery(name = "Emails.findByIdemails", query = "SELECT e FROM Emails e WHERE e.idemails = :idemails"),
        @NamedQuery(name = "Emails.findByEmail", query = "SELECT e FROM Emails e WHERE e.email = :email")})
public class Emails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemails")
    private Integer idemails;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;

    @JoinColumn(name = "google_accounts_idgoogle_accounts", referencedColumnName = "idgoogle_accounts")
    @OneToOne
    private GoogleAccounts googleAccountsIdgoogleAccounts;

    @JoinColumn(name = "person_idperson", referencedColumnName = "idperson")
    @ManyToOne(optional = false)
    private Person personIdperson;

    public Emails() {
    }

    public Emails(Integer idemails) {
        this.idemails = idemails;
    }

    public Emails(Integer idemails, String email) {
        this.idemails = idemails;
        this.email = email;
    }

    public Integer getIdemails() {
        return idemails;
    }

    public void setIdemails(Integer idemails) {
        this.idemails = idemails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GoogleAccounts getGoogleAccountsIdgoogleAccounts() {
        return googleAccountsIdgoogleAccounts;
    }

    public void setGoogleAccountsIdgoogleAccounts(GoogleAccounts googleAccountsIdgoogleAccounts) {
        this.googleAccountsIdgoogleAccounts = googleAccountsIdgoogleAccounts;
    }

    public Person getPersonIdperson() {
        return personIdperson;
    }

    public void setPersonIdperson(Person personIdperson) {
        this.personIdperson = personIdperson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emails emails = (Emails) o;
        return Objects.equals(idemails, emails.idemails) &&
                Objects.equals(email, emails.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idemails, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Emails{");
        sb.append("idemails=").append(idemails);
        sb.append(", email='").append(email).append('\'');
        if (googleAccountsIdgoogleAccounts != null) sb.append(", googleAccountsIdgoogleAccounts=").append(googleAccountsIdgoogleAccounts.getIdgoogleAccounts());
        sb.append(", personIdperson=").append(personIdperson.getIdperson());
        sb.append('}');
        return sb.toString();
    }
}
