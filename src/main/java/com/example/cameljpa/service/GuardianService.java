package com.example.cameljpa.service;

import com.example.cameljpa.controller.dto.CreateCamelRequestDto;
import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Gender;
import com.example.cameljpa.model.Guardian;
import com.example.cameljpa.rest.CamelRepository;
import com.example.cameljpa.rest.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GuardianService {
    private GuardianRepository guardianRepository;
    private CamelRepository camelRepository;

    public GuardianService(GuardianRepository guardianRepository, CamelRepository camelRepository) {
        this.guardianRepository = guardianRepository;
        this.camelRepository = camelRepository;
    }

    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }

    public Guardian create(Guardian guardian) {
        return guardianRepository.save(guardian);
    }

    public Camel assignCamel(Long guardianId, Long camelId) {
        Optional<Camel> byId = camelRepository.findById(camelId);
        Camel camel1 = byId.get();
        Guardian guardian1 = guardianRepository.findById(guardianId).get();
        if (camel1.getGuardians().size() > 0) {
            camel1.getGuardians().add(guardian1);
        } else {
            camel1.setGuardians(guardian1);
        }
        camelRepository.save(camel1);
        guardianRepository.save(guardian1);
        return camel1;
    }

    public void deleteGuardians() {
        guardianRepository.deleteAll();
    }
}
