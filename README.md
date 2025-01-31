# Instructions:
## Run the JAR file
    - Clone repository
    - Navigate to the project directory
        - JAR file is in project directory
        - data.txt is in project root folder to test w/ JAR
    - Run in command-line:
        - java -jar HouseholdSorting.jar data.txt

# Requirements of the Program
## Functionalities
### Read data from data.txt
    - Contains:
        - First name
        - Last name
        - Address  
        - Age
        - City
        - State
    - Format: comma-separated and enclosed in double quotes
### Process data to group individuals by household (address)
    - Household is DEFINED by address
    - Edit: Some addresses have different formatting (e.g., "123 main st." vs "123 Main St"
    - Need to normalize the addresses to group them correctly
###  Filter data to exclude anyone under 19
    - Adults 19 and older should be in input (inclusive)
    - Not expected of us, but if people have same last names, then order by first name
### Display the output in correct format
    - [Household Group ID] [Total number of household occupants]  
            [First Name] [Last Name] [Address] [Age] 
            [First Name] [Last Name] [Address] [Age]

## Components
### Person class
    - Represents an individual
### DataProcessor class
    - Handles all data operations
        - read data
        - process data
        - filtering
        - sorting
        - generating output
### Main
    - Program that calls all our classes and methods

#### Notes:
    - Don't think we need to store internal state of our data processor, so we'll just be making
    method calls on the class itself
        - create static methods