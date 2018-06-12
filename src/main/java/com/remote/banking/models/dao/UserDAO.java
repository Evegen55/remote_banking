package com.remote.banking.models.dao;

import com.remote.banking.models.for_rdbms.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    private EntityManager entityManager;
    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    private static final Class<Person> personClass = Person.class;

    @PostConstruct
    protected void setup() {
        entityManager = localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory()
                .createEntityManager();
    }

    @Autowired
    private EmailsRepository emailsRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Person findById(final int idperson) {
        LOGGER.info("Start find {} by next id: {}", personClass, idperson);
        final Person person = entityManager.find(personClass, idperson);
        return person;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Person> findAllPersons() {
        LOGGER.info("Start find all {}", personClass);
        final TypedQuery<Person> query = entityManager.createNamedQuery("Person.findAll", personClass);
        final List<Person> results = query.getResultList();
        return results;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int createAndStoreNewUser(String firstName, String lastName, LocalDate of, String gender) {
        LOGGER.info("Creating new person with NO emails");
        final Person person = new Person(firstName, lastName, of, gender);
        return createAndStoreNewUser(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int createAndStoreNewUser(final Person person) {
        LOGGER.info("Creating new person with NO emails");
        final Person savedPerson = userRepository.save(person);
        LOGGER.info("{} with NO emails has been created", person);
        return savedPerson.getIdperson();
    }

    public void deleteUserAndSkipId(final int userId) {
        // TODO: 05.04.2018 implement this method
    }
}
