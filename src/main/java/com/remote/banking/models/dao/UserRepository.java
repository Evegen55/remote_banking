package com.remote.banking.models.dao;

import com.remote.banking.models.for_rdbms.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, Long> {

}
