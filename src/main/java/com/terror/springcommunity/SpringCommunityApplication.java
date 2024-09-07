package com.terror.springcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCommunityApplication.class, args);
    }

}
