package com.example.cameljpa.controller.dto;

import com.example.cameljpa.model.Guardian;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCamelReportResponseDto {
    private Long camelId;
    private String camelName;

    private List<Guardian> guardians;
    private String inspectorName;
    private String titleMessage;

}
