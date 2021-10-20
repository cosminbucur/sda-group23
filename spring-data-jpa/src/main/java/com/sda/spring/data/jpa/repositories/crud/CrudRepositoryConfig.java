package com.sda.spring.data.jpa.repositories.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

// spring knows CrudRepositoryConfig
@Configuration
public class CrudRepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(CrudRepositoryConfig.class);

    // 1. main
    // 2. test
    // 3. config with command line runner

    // spring will inject PersonCrudRepository in CrudRepositoryConfig
    @Autowired
    PersonCrudRepository personCrudRepository;

    // spring knows crudData
    @Bean
    public CommandLineRunner crudData() {
        return (strings) -> {
            // create
            Person alex = new Person("alex", 20);
            Person ana = new Person("ana", 22);
            personCrudRepository.save(alex);
            personCrudRepository.save(ana);

            // read
            testRead();

            // update
            testUpdate(alex);

            // delete
            testDelete(ana);
        };
    }

    private void testRead() {
        Iterable<Person> people = personCrudRepository.findAll();
        // interface = implementation

        // print with system out
        Consumer<Person> personConsumer = person -> System.out.println(person.getName());
        people.forEach(personConsumer);

        // print with logger
        people.forEach(person -> log.info(person.getName()));
    }

    private void testUpdate(Person person) {
        // create a person
        // read person from db
        Person personToUpdate = personCrudRepository.findById(person.getId())
                .orElseThrow(() -> new RuntimeException("person not found"));
        // change person
        personToUpdate.setAge(personToUpdate.getAge() + 5);

        // save update person to db
        Person updatedPerson = personCrudRepository.save(personToUpdate);

        // read updated person from db
        System.out.println(updatedPerson);
    }

    private void testDelete(Person person) {
        personCrudRepository.deleteById(person.getId());

        long count = personCrudRepository.count();
        System.out.println("people left " + count);
    }

    private void rightOptional(Person person) {
        // optional - right
        Person easyPerson = personCrudRepository.findById(person.getId())
                .orElseThrow(() -> new RuntimeException("person not found"));
    }

    private void wrongOptional(Person person) {
        // optional - wrong
        Optional<Person> alexOptional = personCrudRepository.findById(person.getId());
        if (alexOptional.isPresent()) {
            Person realAlex = alexOptional.get();
            System.out.println("real alex " + realAlex.getName());
        }
    }

}
