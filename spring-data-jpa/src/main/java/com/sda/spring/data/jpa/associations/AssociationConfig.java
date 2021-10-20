package com.sda.spring.data.jpa.associations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssociationConfig {

    @Autowired
    private FatherRepository fatherRepository;

    @Bean
    public CommandLineRunner associationData() {
        return strings -> testAssociation();
    }

    private void testAssociation() {
        Son son1 = new Son("son 1");
        Son son2 = new Son("son 2");
        Father father = new Father("father");

        father.addSon(son1);
        father.addSon(son2);
        fatherRepository.save(father);
    }
}
