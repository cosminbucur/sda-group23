package com.sda.spring.data.jpa.repositories.jpa;

import com.sda.spring.data.jpa.repositories.crud.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Configuration
public class PersonJpaRepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(PersonJpaRepositoryConfig.class);

    @Autowired
    public PersonJpaRepository repository;

    @Bean
    public CommandLineRunner jpaData() {
        return args -> {
            loadData();

            // crud - jpa

            // get first person id
            long idOfFirstPerson = repository.findAll().get(0).getId();

            // find one person
            Person firstPerson = repository.getOne(idOfFirstPerson);

            // first 2 people
            Pageable firstTwoElements = PageRequest.of(0, 2);
            Page<Person> firstTwoPeople = repository.findAll(firstTwoElements);

            // delete in batch
            Iterable<Person> peopleToDelete = firstTwoPeople.getContent();
            repository.deleteInBatch(peopleToDelete);

            repository.deleteAllInBatch();
        };
    }

    private void printPageContent(Page<Person> page) {
        page.getContent()
                .forEach(person -> log.info("person: {}", person));
    }

    private void loadData() {
        repository.save(new Person("paul", 32));
        repository.save(new Person("alina", 28));
        repository.save(new Person("kazimir", 34));
        repository.save(new Person("lidia", 32));
        repository.save(new Person("violeta", 32));
    }
}
