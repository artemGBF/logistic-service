package ru.gbf.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gbf.cartservice.model.Good;
import ru.gbf.cartservice.repository.CartRepository;

import java.util.Map;

@SpringBootApplication
public class CartServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Autowired
    private CartRepository repository;

    @Override
    public void run(String... args) throws Exception {
    }
}
