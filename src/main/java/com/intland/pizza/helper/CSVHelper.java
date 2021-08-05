package com.intland.pizza.helper;

import com.intland.pizza.persistence.model.Pizza;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    public static List<Pizza> csvToPizzas(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT)) {

            List<Pizza> pizzas = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Pizza pizza = new Pizza();
                pizza.setName(csvRecord.get(0));
                pizza.setPrice(Integer.parseInt(csvRecord.get(1)));
                List<String> toppings = new ArrayList<>();
                int limit = 7;
                if (csvRecord.size() < 7) {
                    limit = csvRecord.size();
                }
                for (int i = 2; i < limit; i++) {
                    String topping = csvRecord.get(i);
                    if (topping != null && !topping.isEmpty()) {
                        toppings.add(topping);
                    }
                }
                pizza.setToppings(toppings);
                pizzas.add(pizza);
            }
            return pizzas;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
