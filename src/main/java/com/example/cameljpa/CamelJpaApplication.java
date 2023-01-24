package com.example.cameljpa;

import com.example.cameljpa.controller.dto.CreateCamelRequestDto;
import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Gender;
import com.example.cameljpa.model.Guardian;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelJpaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(CamelJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

//        Camel camel = new Camel("Jan", 1, Gender.FEMALE,
//                new Guardian("Jan", "jan@mail.com", "admin"));
//        CreateCamelRequestDto.builder().
//                age(1).name("Jan").gender(Gender.MALE).build();
//        System.out.println(camel);

    }


}
