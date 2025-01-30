package org.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataProcessor {
    /*
        readData() method:
        - read file and extract data for each person
        - convert each line into Person object
        - store all Person objects into a list
        - normalize the data so we can group/sort/filter
        - return List<Person>
     */
    public static List<Person> readData(String file) throws IOException {
        List<Person> people = new ArrayList<>();

        // https://www.baeldung.com/java-buffered-reader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                /*
                    - line = line.substring(1, line.length() - 1); remove outer quotes
                    - String[] values = line.split("\",\""); split by ","
                */
                String[] values = line.substring(1, line.length() - 1).split("\",\"");

                // Extract data from line, remove any leading/trailing whitespace
                // Edit: added an edge-case to make sure String array is of size 6 so we don't get an index error
                if (values.length == 6) {
                    String firstName = values[0].trim();
                    String lastName = values[1].trim();
                    String address = values[2].trim();
                    int age = Integer.parseInt(values[5].trim());

                    // Create person object
                    Person person = new Person(firstName, lastName, address, age);
                    people.add(person);
                } else {
                    System.out.println("Skipping line due to wrong format");
                }
            }
        } catch (IOException e) {
            System.out.println("Error in reading file: " + e.getMessage());
        }

        return people;
    }

    // Group people by household based on address
    // return grouped households key-value map
    // key: address string
    // value: person object
    public static Map<String, List<Person>> groupByHousehold(List<Person> people) {
        Map<String, List<Person>> households = new HashMap<>();

        for (Person person : people) {
            String rawAddress = person.getAddress();
            String normalizedAddress = normalizeAddress(rawAddress);

            if (!households.containsKey(normalizedAddress)) {
                households.put(normalizedAddress, new ArrayList<>());
            }

            households.get(normalizedAddress).add(person);
        }

        return households;
    }

    public static String normalizeAddress(String address) {
        // For regex:
        // https://www.baeldung.com/java-remove-punctuation-from-string
        // https://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead
        // use trim for good measure
        return address.toLowerCase()
                .replaceAll("[.,]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }


    public static void displayHouseholds(Map<String, List<Person>> households) {
        // Go into each household
        // 1. print out [address] [totalOccupants]
        // 2. filter out anyone under 19
        // 3. add them to over19Occupants list
        // 4. sort the household by last name
        // 5. generate output
        for (Map.Entry<String, List<Person>> entry : households.entrySet()) {
            String address = entry.getKey();

            List<Person> occupants = entry.getValue();

            int totalOccupants = occupants.size();
            System.out.println(address + " " + totalOccupants);

            List<Person> over19Occupants = new ArrayList<>();
            for (Person person : occupants) {
                if (person.getAge() >= 19) {
                    over19Occupants.add(person);
                }
            }

            // https://www.geeksforgeeks.org/comparator-interface-java/
            over19Occupants.sort(Comparator.comparing(Person::getLastName)
                    .thenComparing(Person::getFirstName));

            // print out the occupants
            for (Person person : over19Occupants) {
                System.out.println("    " + person);
            }
        }
    }
}
