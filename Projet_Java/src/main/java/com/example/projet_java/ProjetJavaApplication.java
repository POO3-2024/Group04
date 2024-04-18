package com.example.projet_java;

import com.example.projet_java.javafx.HelloFX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetJavaApplication.class, args);
        HelloFX.main(args);
    }

}
