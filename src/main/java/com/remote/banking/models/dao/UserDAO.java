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


}
