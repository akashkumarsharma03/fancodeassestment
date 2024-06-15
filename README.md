# Fancode Assessment

This project automates a scenario using RestAssured with Java over TestNG framework. The JSON responses are serialized into POJO classes using Jackson. Allure is used for reporting.

## Scenario

All the users of the city `FanCode` should have more than half of their todos tasks completed.

### Given:
- User has todo tasks.
- User belongs to the city `FanCode`.

### Then:
- User completed task percentage should be greater than 50%.

## Project Structure

- `/src/test/java/models`: Contains the POJO classes for User and Todo models.
- `/src/test/java/fancodeApi.java`: Contains the API tests.
- `testng.xml`: Configuration file to run the test class.

## Dependencies

- **RestAssured**: For API testing.
- **Jackson**: For JSON serialization and deserialization.
- **Allure**: For reporting.
- **TestNG**: For running the tests.

## Prerequisites

- Java 11 or higher
- Maven

## Setup and Execution

1. **Clone the repository**:
    ```sh
    git clone https://github.com/akashkumarsharma03/fancodeassestment.git
    cd fancodeassestment
    ```

2. **Run the tests**:
    ```sh
    mvn clean test
    ```

3. **Generate the Allure report**:
    ```sh
    mvn allure:report
    ```

4. **Serve the Allure report**:
    ```sh
    mvn allure:serve
    ```

#### *You can also find all different type of TEST_NG html reports in the given paths:*

- **Fancode/target/surefire-reports/index.html**
-  **Fancode/target/surefire-reports/emailable-report.html**
- **FancodeAssestmnet/Fancode/target/surefire-reports/fancode Assestmnet/All.html**

