package com.amit.springsercurity;

import com.amit.springsercurity.util.ICheckBCryptPasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSercurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSercurityApplication.class, args);
    }
    @Bean
    public ICheckBCryptPasswordEncoder passwordEncoder(){
        return new ICheckBCryptPasswordEncoder();
    }

}
