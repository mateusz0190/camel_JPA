package com.example.cameljpa.controller.dto;

import com.example.cameljpa.model.Camel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCamelResponseDto {
    private Camel camelNumber;
}
