
# Java Data Processing Application

## Project Overview

This Java project is designed to read, process, and write integer data from both console and file sources.

## Prerequisites

- Java JDK 11 or higher
- Maven (optional, if dependencies need to be managed)

## Setup and Configuration

1. **Build the project (if using Maven):**
   ```bash
   mvn clean install
   ```

## Running the Application

The application can be started by running the `App.java` class. You can run the application from the command line:

```bash
java -cp target/number-filter-app-0.0.1.jar com.sasimykyta.App <input parameter> 
```

Replace `target/number-filter-app-0.0.1.jar` with the path to your compiled JAR if necessary.

### Using Console for Input/Output

- Start the application with input parameter argument to trigger console data reading and writing:
  ```bash
  java -cp target/number-filter-app-0.0.1.jar com.sasimykyta.App 4
  ```

### Using Files for Input/Output

- Provide the path to the input file as the first argument and optionally the path to the output file as the second argument:
  ```bash
  java -cp target/number-filter-app-0.0.1.jar com.sasimykyta.App input.txt output.txt
  ```

## Running Tests

To execute tests:

```bash
mvn test
```

