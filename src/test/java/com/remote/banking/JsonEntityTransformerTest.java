package com.remote.banking;

import com.remote.banking.models.for_rdbms.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEntityTransformerTest {

    @Test
    public void toJson() {
        Person person = new Person();
        person.setFirstName("ABC");
        person.setLastName("DEF");
        String s1 = JsonEntityTransformer.toJson(person);
        System.out.println(s1);
    }
}