package com.luminahi.datah2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringDataApplication {
    
    private static final Logger log = LoggerFactory.getLogger(SpringDataApplication.class); 
    
    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CustomerRepository repository) {
        return (arg) -> {
            // save some customers
            repository.save(new Customer("sarah", "kasugano"));
            repository.save(new Customer("karen", "smith"));
            repository.save(new Customer("robert", "star"));
            // fetch all customers
            log.info("");
            log.info("all registered customers");
            log.info("---------------------------------------");
            repository
                .findAll()
                .forEach(customer -> log.info(customer.toString()));
            log.info("");
            // fetch a customer by ID
            log.info("found customer by ID");
            log.info("---------------------------------------");
            Optional<Customer> customer = repository.findById(10l);
            customer.ifPresentOrElse(
                    (c) -> log.info(c.toString()),
                    () -> log.info("Not Found")
                );
            log.info("");
        };
    }
    
}
