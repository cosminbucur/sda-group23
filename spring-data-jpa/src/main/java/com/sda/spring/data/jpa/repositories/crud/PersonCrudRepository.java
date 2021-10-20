package com.sda.spring.data.jpa.repositories.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// spring knows PersonCrudRepository
// @Repository is optional
@Repository
public interface PersonCrudRepository extends CrudRepository<Person, Long> {

}
