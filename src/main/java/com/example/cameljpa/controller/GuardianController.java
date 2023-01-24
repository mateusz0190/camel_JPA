package com.example.cameljpa.controller;

import com.example.cameljpa.controller.dto.CreateGuardianRequestDto;
import com.example.cameljpa.controller.dto.CreateGuardianResponseDto;
import com.example.cameljpa.model.Guardian;
import com.example.cameljpa.service.GuardianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardian")
@RequiredArgsConstructor
public class GuardianController {
    private final GuardianService guardianService;

    @PostMapping
    public ResponseEntity<CreateGuardianResponseDto> createGuardian(@RequestBody CreateGuardianRequestDto guardianRequestDto) {
        return ResponseEntity.ok(CreateGuardianResponseDto.builder()
                .guardian(guardianService.create(Guardian.builder()
                        .name(guardianRequestDto.getName())
                        .lastName(guardianRequestDto.getLastName())
                        .email(guardianRequestDto.getEmail())
                        .build())).build());
    }

    @GetMapping
    public ResponseEntity<List<Guardian>> getAllGuardians() {
        return ResponseEntity.ok(guardianService.getAllGuardians());
    }

    @GetMapping("/api/{gid}/{cid}")
    @ResponseBody
    public ResponseEntity<HttpStatus> assignCamel(@PathVariable("gid") String gId, @PathVariable("cid") String cId) {
        Long guardianId = Long.parseLong(gId);
        Long camelId = Long.parseLong(cId);
        guardianService.assignCamel(guardianId, camelId);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteGuardians() {
        guardianService.deleteGuardians();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
