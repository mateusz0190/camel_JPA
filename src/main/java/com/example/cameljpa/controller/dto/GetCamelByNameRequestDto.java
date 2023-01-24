package com.example.cameljpa.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetCamelByNameRequestDto {
    private String requestName;
}
