package dev.mvc.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.mvc")
@EntityScan(basePackages = "dev.mvc")
@ComponentScan(basePackages = {"dev.mvc"})
public class Team5_v2sbm3cApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5_v2sbm3cApplication.class, args);
    }

}
