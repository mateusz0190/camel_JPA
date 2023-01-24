package com.example.cameljpa.service;

import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Inspector;
import com.example.cameljpa.rest.CamelRepository;
import com.example.cameljpa.rest.InspectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InspectorService {
    private InspectorRepository inspectorRepository;
    private CamelRepository camelRepository;


    public Inspector create(Inspector inspector) {
        return inspectorRepository.save(inspector);
    }

    public List<Inspector> getInspectors() {
        return inspectorRepository.findAll();
    }

    public Inspector getInspectorById(Long id) {
        return inspectorRepository.findById(id).get();
    }

    public Inspector getInspectorByCamelId(Long camelId) {
        List<Inspector> inspectors = getInspectors();

        for (Inspector inspector : inspectors) {

            List<Camel> camels = inspector.getCamels();
            Camel camel1= camels.stream().filter(camel -> camel.getId() == camelId).findFirst().get();
            if(camel1.getId()>0){
                return inspector;
            }

        }
        return new Inspector();
    }

    public Inspector assignCamelToInspector(List<Camel> camels, Long inspectorId) {
        Inspector inspector = getInspectorById(inspectorId);
        if (inspector.getCamels().size() > 0) {
            camels.forEach(camel -> inspector.getCamels().add(camel));

        } else {
            inspector.setCamels(camels);
        }
        camelRepository.save(camels.get(0));
        inspectorRepository.save(inspector);
        return inspector;
    }
}
