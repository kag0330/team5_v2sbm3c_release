package dev.mvc.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class Team5_v2sbm3cApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5_v2sbm3cApplication.class, args);
    }

}
