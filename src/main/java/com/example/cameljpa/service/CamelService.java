package com.example.cameljpa.service;

import com.example.cameljpa.model.Camel;
import com.example.cameljpa.rest.CamelRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelService {
    private CamelRepository camelRepository;

    public CamelService(CamelRepository camelRepository) {
        this.camelRepository = camelRepository;
    }

    public List<Camel> getAll() {
        return camelRepository.findAll();
    }

    public List<Camel> getCamelByName(String camelName) {
        List<Camel> camelList = new ArrayList<>();
        getAll().stream().filter(c -> c.getName().equals(camelName)).forEach(camel -> camelList.add(camel));
        return camelList;
    }

    public Camel getCamelById(Long id) {
        return camelRepository.findById(id).get();
    }

    public Camel create(Camel camel) {
        return camelRepository.save(camel);
    }

    public void deleteCamels() {
        camelRepository.deleteAll();
    }
}
