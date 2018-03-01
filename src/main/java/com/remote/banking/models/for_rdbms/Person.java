package com.remote.banking.models.for_rdbms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Evgenii_Lartcev
 */
@Entity
@Table(name = "person")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.findByIdperson", query = "SELECT p FROM Person p WHERE p.idperson = :idperson"),
        @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
        @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
        @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth"),
        @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE p.gender = :gender")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperson")
    private Integer idperson;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gender")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personIdperson")
    private List<Emails> emailsList;

    public Person() {
    }

    public Person(Integer idperson) {
        this.idperson = idperson;
    }

    public Person(Integer idperson, String firstName, String lastName, LocalDate dateOfBirth, String gender) {
        this.idperson = idperson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Emails> getEmailsList() {
        return emailsList;
    }

    public void setEmailsList(List<Emails> emailsList) {
        this.emailsList = emailsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(idperson, person.idperson) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(dateOfBirth, person.dateOfBirth) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(emailsList, person.emailsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idperson, firstName, lastName, dateOfBirth, gender, emailsList);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("idperson=").append(idperson);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", emailsList=").append(emailsList);
        sb.append('}');
        return sb.toString();
    }
}
