package com.remote.banking.models.dao;

import com.remote.banking.models.for_rdbms.Emails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmailsRepository  extends PagingAndSortingRepository<Emails, Long> {
}
