package com.sda.spring.data.jpa.service;

import com.sda.spring.data.jpa.repositories.crud.Person;
import com.sda.spring.data.jpa.repositories.pagination.PersonPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    // view all users by first name ascending

    // gigi
    // florin
    // ionut

    private final PersonPagingAndSortingRepository repository;

    @Autowired
    public PersonService(PersonPagingAndSortingRepository repository) {
        this.repository = repository;
    }

    public List<String> filterPeople() {
        Pageable sortedByAgeDescendingAndNameAscending = PageRequest.of(0, 5,
                Sort.by("age").descending()
                        .and(Sort.by("name").ascending())
        );
        Page<Person> peopleSortedByAgeDescendingAndNameAscending = repository.findAll(sortedByAgeDescendingAndNameAscending);
        return peopleSortedByAgeDescendingAndNameAscending.getContent()
                .stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());
    }
}
