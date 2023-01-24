package com.example.cameljpa.controller;

import com.example.cameljpa.controller.dto.CreateCamelRequestDto;
import com.example.cameljpa.controller.dto.CreateCamelResponseDto;
import com.example.cameljpa.controller.dto.GetCamelByNameRequestDto;
import com.example.cameljpa.controller.dto.GetCamelByNameResponseDto;
import com.example.cameljpa.helper.CsvHelper;
import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Guardian;
import com.example.cameljpa.service.CSVService;
import com.example.cameljpa.service.CamelService;
import com.example.cameljpa.service.GuardianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/camel")
@RequiredArgsConstructor
public class CamelController {
    private final CamelService camelService;
    private final CSVService csvService;


    @PostMapping
    public ResponseEntity<CreateCamelResponseDto> addCamel(@RequestBody CreateCamelRequestDto camelRequestDto) {
        return ResponseEntity.ok(CreateCamelResponseDto.builder()
                .camelNumber(camelService
                        .create(Camel.builder().name(camelRequestDto.getName())
                                .gender(camelRequestDto.getGender())
                                .age(camelRequestDto.getAge())
                                .build())).build());
    }

    @GetMapping
    public ResponseEntity<List<Camel>> getAllCamels() {
        return ResponseEntity.ok().body(camelService.getAll());
    }

    @GetMapping(value = "/{camelName}")
    public ResponseEntity<List<Camel>> getCamelByName(@PathVariable("camelName") String camelName) {
        return ResponseEntity.ok().body(camelService.getCamelByName(camelName));
    }

    @GetMapping(value = "/custom/{camelName}")
    public ResponseEntity<List<GetCamelByNameResponseDto>> getCamelAndGuardianByName(@PathVariable("camelName") String camelName) {
        List<GetCamelByNameResponseDto> responseDtos = new ArrayList<>();
        List<Camel> camels = camelService.getCamelByName(camelName);

        for (Camel camel :
                camels) {
            List<String> guardiansName = new ArrayList<>();
            List<String> guardiansEmail = new ArrayList<>();
            camel.getGuardians().forEach(
                    guardian -> {
                        guardiansName.add(guardian.getName());
                        guardiansEmail.add(guardian.getEmail());
                    });

            GetCamelByNameResponseDto responseDto = GetCamelByNameResponseDto.builder()
                    .camelName(camel.getName())
                    .camelAge(camel.getAge())
                    .guardiansName(guardiansName)
                    .guardiansEmail(guardiansEmail).build();
            responseDtos.add(responseDto);
        }
        return ResponseEntity.ok().body(responseDtos);
    }

    @PostMapping(value = "/csv")
    public ResponseEntity<HttpStatus> addCamelsCsv(@RequestParam("file") MultipartFile file) {
        if (CsvHelper.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                return ResponseEntity.ok(HttpStatus.CREATED);
            } catch (IOException e) {
                return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.CONFLICT);
            }
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaData() {
        camelService.deleteCamels();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
