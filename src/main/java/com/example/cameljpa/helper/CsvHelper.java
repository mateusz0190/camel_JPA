package com.example.cameljpa.helper;

import com.example.cameljpa.model.Camel;
import com.example.cameljpa.model.Gender;
import com.example.cameljpa.model.Guardian;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"name", "age", "gender"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Camel> CSVtoCamel(InputStream inputStream) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            List<Camel> camels = new ArrayList<>();

            for (CSVRecord csvRecord : csvRecords
            ) {
                Camel camel = Camel.builder()
                        .name(csvRecord.get(0))
                        .age(Integer.parseInt(csvRecord.get(1)))
                        .gender(Gender.valueOf(csvRecord.get(2)))
                        .build();

                camels.add(camel);

            }
            return camels;
        } catch (IOException e) {
            throw new RuntimeException("Failed to import");
        }
    }
}
