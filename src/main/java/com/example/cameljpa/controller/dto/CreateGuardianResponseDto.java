package com.example.cameljpa.controller.dto;

import com.example.cameljpa.model.Guardian;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateGuardianResponseDto {
    private Guardian guardian;
}
