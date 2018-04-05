package com.remote.banking.models.dao;

import com.remote.banking.models.for_rdbms.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository  extends PagingAndSortingRepository<Person, Long> {

}
