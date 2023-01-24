package com.example.cameljpa.service;

import com.example.cameljpa.helper.CsvHelper;
import com.example.cameljpa.model.Camel;
import com.example.cameljpa.rest.CamelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    private CamelRepository camelRepository;

    public CSVService(CamelRepository camelRepository) {
        this.camelRepository = camelRepository;
    }

    public void save(MultipartFile multipartFile) throws IOException {
        try {
            List<Camel> camelEntities = CsvHelper.CSVtoCamel(multipartFile.getInputStream());
            camelRepository.saveAll(camelEntities);

        } catch (IOException e) {
            throw new RuntimeException("problemm" + e.getMessage());
        }
    }
}
