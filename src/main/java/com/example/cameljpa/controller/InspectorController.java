package com.example.cameljpa.controller;

import com.example.cameljpa.controller.dto.GetCamelReportResponseDto;
import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Inspector;
import com.example.cameljpa.service.CamelService;
import com.example.cameljpa.service.InspectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inspector")
public class InspectorController {
    private final InspectorService inspectorService;
    private final CamelService camelService;

    @PostMapping
    public ResponseEntity<Inspector> createInspector(@RequestBody Inspector inspector) {
        return ResponseEntity.ok(inspectorService.create(Inspector.builder()
                .name(inspector.getName())
                .build()));
    }

    @GetMapping
    public ResponseEntity<List<Inspector>> getAllInspectors() {
        return ResponseEntity.ok(inspectorService.getInspectors());
    }

    @PutMapping("/{camelName}/{iId}")
    public ResponseEntity<Inspector> assignCamelToInspector(@PathVariable("camelName") String camelName, @PathVariable("iId") Long inspectorId) {
        List<Camel> camels = camelService.getCamelByName(camelName);
        Inspector inspector = inspectorService.assignCamelToInspector(camels, inspectorId);
        return ResponseEntity.ok(inspector);
    }

    @GetMapping(value = "/report{cid}")
    public ResponseEntity<GetCamelReportResponseDto> getCamelReport(@PathVariable("cid") Long camelId) {
        Camel camelById = camelService.getCamelById(camelId);

        return ResponseEntity.ok(GetCamelReportResponseDto.builder()
                .camelId(camelById.getId())
                .camelName(camelById.getName())
                .guardians(camelById.getGuardians())
                .inspectorName(inspectorService.getInspectorByCamelId(camelId).getName())
                .titleMessage("Report for camel " + camelById.getName())
                .build());
    }
}
