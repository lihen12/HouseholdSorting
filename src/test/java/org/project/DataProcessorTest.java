package org.project;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {

    @Test
    void testReadData() throws IOException {
        // Load the test file from src/test/resources
        Path testFilePath = Paths.get("src/test/resources/test-data.txt");

        // Check if file exists
        assertTrue(Files.exists(testFilePath), "Test data file should exist!");

        // Read data using DataProcessor
        List<Person> people = new DataProcessor().readData(testFilePath.toString());

        // Check if the list is not empty
        assertFalse(people.isEmpty(), "The people list should not be empty!");

        // Validate the first entry
        assertEquals("Dave", people.get(0).getFirstName(), "The first person's name should be Dave.");
    }


    @Test
    void testNormalizeAddress() {
        assertEquals("123 main st", DataProcessor.normalizeAddress("123 Main St."));
        assertEquals("456 oak ave", DataProcessor.normalizeAddress("  456 Oak Ave  "));
        assertEquals("789 pine rd apt 2", DataProcessor.normalizeAddress("789 Pine Rd., Apt 2"));
    }

    @Test
    void testDisplayHouseholds() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", "Smith", "123 Main St.", 30));
        people.add(new Person("Bob", "Jones", "456 Oak St.", 25));
        people.add(new Person("Charlie", "Smith", "123 main st", 17)); // Underage

        // Group people by household
        Map<String, List<Person>> households = DataProcessor.groupByHousehold(people);

        // Ensure there are exactly 2 households
        assertEquals(2, households.size(), "Households should be grouped correctly.");

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call method
        DataProcessor.displayHouseholds(households);

        // Convert captured output into a string
        String output = outputStream.toString();

        // Check that Alice appears
        assertTrue(output.contains("Alice Smith"));

        // Ensure Charlie is not included
        assertFalse(output.contains("Charlie Smith"), "Minors should be filtered out.");

        // Ensure Bob appears
        assertTrue(output.contains("Bob Jones"));

        // Ensure both household addresses are present in the output
        assertTrue(output.contains("123 main st"));
        assertTrue(output.contains("456 oak st"));
    }
}
