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
@Table(name = "google_accounts")
@NamedQueries({
        @NamedQuery(name = "GoogleAccounts.findAll", query = "SELECT g FROM GoogleAccounts g"),
        @NamedQuery(name = "GoogleAccounts.findByIdgoogleAccounts", query = "SELECT g FROM GoogleAccounts g WHERE g.idgoogleAccounts = :idgoogleAccounts"),
        @NamedQuery(name = "GoogleAccounts.findByFamilyName", query = "SELECT g FROM GoogleAccounts g WHERE g.familyName = :familyName")})
public class GoogleAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idgoogle_accounts")
    private Integer idgoogleAccounts;

    @Size(max = 45)
    @Column(name = "family_name")
    private String familyName;

    @OneToOne(mappedBy = "googleAccountsIdgoogleAccounts")
    private Emails emails;

    public GoogleAccounts() {
    }

    public GoogleAccounts(Integer idgoogleAccounts) {
        this.idgoogleAccounts = idgoogleAccounts;
    }

    public Integer getIdgoogleAccounts() {
        return idgoogleAccounts;
    }

    public void setIdgoogleAccounts(Integer idgoogleAccounts) {
        this.idgoogleAccounts = idgoogleAccounts;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Emails getEmails() {
        return emails;
    }

    public void setEmails(Emails emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoogleAccounts that = (GoogleAccounts) o;
        return Objects.equals(idgoogleAccounts, that.idgoogleAccounts) &&
                Objects.equals(familyName, that.familyName) &&
                Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgoogleAccounts, familyName, emails);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GoogleAccounts{");
        sb.append("idgoogleAccounts=").append(idgoogleAccounts);
        sb.append(", familyName='").append(familyName).append('\'');
        sb.append(", emails=").append(emails);
        sb.append('}');
        return sb.toString();
    }
}
