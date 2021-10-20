package com.sda.spring.data.jpa;

import org.springframework.boot.CommandLineRunner;

public class Lambda {

    // how to use lambda

    /*  implement interface
         1. no lambda
         2. anonymous implementation
         3. with lambda
     */

    // option 2
    private void anonymousImplementation() {
        CommandLineRunner commandLineRunner = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("pay me");
            }
        };
    }

    // option 2
    // lambda = (input) -> {body}
    // interface = single method implementation
    private void lambda() {
        // void run(String... args) throws Exception;

        CommandLineRunner lambda = (strings) -> {
            System.out.println("pay me");
        };

        CommandLineRunner simpleLambda =
                (strings) -> System.out.println("pay me");

        CommandLineRunner simplestLambda =
                strings -> System.out.println("pay me");
    }
}
