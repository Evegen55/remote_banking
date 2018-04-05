package com.remote.banking.models.dao;

import com.remote.banking.models.for_rdbms.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    @Test
    public void findById() {
        Person byId = userDAO.findById(1);
        assertTrue(byId.getFirstName().equals("John"));
    }

    @Test
    public void findAllPersons() {
    }

    @Test
    public void createPerson() {
        int userId = userDAO.createAndStoreNewUser("firstName", "lastName",
                LocalDate.of(2014, 8, 14), "man");
        assertTrue(userId > 0);
    }
}