package com.sda.spring.data.jpa.repositories.crud;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    // required to identify entities
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;    // defaults to null

    private Integer age;    // defaults to null

    // empty constructor - required by hibernate
    public Person() {}

    // optional constructor
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // required by hibernate
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // used for sorting
    @Override
    public boolean equals(Object otherPerson) {
        if (this == otherPerson) return true;
        if (otherPerson == null || getClass() != otherPerson.getClass()) return false;
        Person person = (Person) otherPerson;
        return Objects.equals(id, person.id);
    }

    // preserve uniqueness
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // visual representation of the state
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
