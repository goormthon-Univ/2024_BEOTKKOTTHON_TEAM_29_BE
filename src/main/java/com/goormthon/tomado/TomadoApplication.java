package com.goormthon.tomado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TomadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomadoApplication.class, args);
    }

}
