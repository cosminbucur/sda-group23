package com.sda.spring.data.jpa;

import com.sda.spring.data.jpa.repositories.crud.Person;

import java.util.Map;

public class InMemoryDb {

    static Map<Long, Person> myInMemoryDb = Map.of(
            1L, new Person("ana", 30),
            2L, new Person("alex", 25)
    );

    public static void main(String[] args) {
        Person ana = myInMemoryDb.get(1L);
        System.out.println(ana);
        Person alex = myInMemoryDb.get(2L);
        System.out.println(alex);
    }
}
