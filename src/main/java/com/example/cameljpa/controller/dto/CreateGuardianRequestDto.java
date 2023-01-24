package com.example.cameljpa.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateGuardianRequestDto {
    private String name;
    private String lastName;
    private String email;
}
