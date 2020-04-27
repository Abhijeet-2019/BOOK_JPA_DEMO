package com.abhijeet.books.libraryApp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.abhijeet.books.libraryApp")
@EnableAutoConfiguration
@SpringBootConfiguration
@EntityScan(basePackages = {"com.abhijeet.books.libraryApp.model"} )
@EnableJpaRepositories("com.abhijeet.books.libraryApp.repo")
public class LibaryApp {

    public static void main(String[] args) {
        System.out.println("Starting  Library App");
        try{
            SpringApplication.run(LibaryApp.class);
        }catch (Exception e){
            System.out.println(" Unable to Start the App");
        }
        System.out.println("Started The Library App");
    }

}
