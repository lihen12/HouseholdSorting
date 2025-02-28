package org.project;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/resources/data2.txt";

        List<Person> people = DataProcessor.readData(filePath);
        Map<String, List<Person>> households = DataProcessor.groupByHousehold(people);
        DataProcessor.displayHouseholds(households);
    }
}
