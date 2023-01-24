package com.example.cameljpa.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
@Data
@AllArgsConstructor
public class GetCamelByNameResponseDto {
    private String camelName;
    private Integer camelAge;
    private List<String> guardiansName;
    private List<String> guardiansEmail;
}
