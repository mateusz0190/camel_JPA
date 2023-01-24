package com.example.cameljpa.controller.dto;

import com.example.cameljpa.model.Gender;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCamelRequestDto {
    private String name;
    private int age;
    private Gender gender;
}
