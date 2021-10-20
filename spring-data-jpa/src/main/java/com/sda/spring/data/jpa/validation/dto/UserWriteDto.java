package com.sda.spring.data.jpa.validation.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserWriteDto {

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Email(message = "invalid email format")
    private String email;

    @AssertTrue(message = "consent must be accepted")
    private Boolean consented;

    @Size(min = 10, max = 100, message = "description should be between 10 - 100 characters")
    private String aboutMe;

    @Min(value = 18, message = "age should be at least 18")
    @Max(value = 80, message = "age should be at maximum 80")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConsented() {
        return consented;
    }

    public void setConsented(Boolean consented) {
        this.consented = consented;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", consented=" + consented +
                ", aboutMe='" + aboutMe + '\'' +
                ", age=" + age +
                '}';
    }
}
