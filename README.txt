Prerequisites
Before you continue, ensure you meet the following requirements:
* You have installed JDK and add JAVA_HOME system variable
* You have to install MAVEN and add MAVEN_HOME system variable

Run test suite/case
$ cd automationtesting
* To run test suite
 mvn test
* To run test case
 mvn -Dtest=TestCases#CheckRepaymentTab test

Architectural decisions: Page Object Model (POM)
Tech Stack: Java, Selenium and TestNG
Structure
configs folder: properties file that contain master data like url, admin account etc
src/main/java
 + common folder: contain all common method: UI, API ect
 + constants: contain java class to delare ENUM
 + dataProvider: java class to do every related to read/write file
 + pageobjects: each java class is each page on website contain all item on website page: Xpath, fields, actions(methods).
src/test/java: contain all testcases
