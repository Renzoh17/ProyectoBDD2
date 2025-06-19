package com.main.proyectobdd2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProyectoBdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoBdd2Application.class, args);
    }

}
