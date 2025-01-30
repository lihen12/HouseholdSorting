package org.project;

public class Person {
    // Fields
    private String firstName;
    private String lastName;
    private String address;
    private int age;

    // Initialize person
    public Person(String firstName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    // Getters (don't need setters)
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public int getAge() {
        return this.age;
    }

    // toString() method to print the details in the required format
    // [First Name] [Last Name] [Address] [Age]
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + address + " " + age;
    }
}
