Ecommerce Test Automation Framework
Project Overview
This project is an Ecommerce Test Automation Framework designed to automate the testing of various functionalities of the Pepperfry website. The framework primarily uses Selenium WebDriver for UI automation, TestNG for running tests, and Maven for project management. The reports are generated using Allure for detailed test reporting.

Features
Automated UI Testing: The framework is built to perform end-to-end testing of the Pepperfry website, including functionalities like login, cart management, product search, Payments and wishlist management.
Cross-Browser Testing: The framework supports multiple browsers (Chrome, Firefox) for cross-browser compatibility testing.
Parameterized Testing: Using TestNG, the framework supports running tests with multiple sets of data.
Report Generation: Allure Reports are integrated into the framework to provide easy-to-read, interactive test execution reports.
Page Object Model (POM): The framework uses the Page Object Model design pattern to separate the test logic from the web page's UI interactions.
Project Setup
Prerequisites
Before you begin, ensure you have met the following requirements:

Java: Version 11 or higher.
Maven: Ensure that Maven is installed on your machine for project dependency management and running tests.
Selenium WebDriver: The framework uses Selenium for UI automation.
TestNG: For running and managing the tests.
Allure: For generating reports.
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/Sumit-9669/Ecommerce_Automation.git
Navigate to the project directory:

bash
Copy code
cd Ecommerce_Automation
Install the required dependencies using Maven:

bash
Copy code
mvn clean install
Run the tests:

bash
Copy code
mvn test
Test Automation Details
Test Structure
TestNG: The framework uses TestNG as the test runner. Each test case is written as a TestNG method.
Selenium: All the UI interactions with the web elements on the Pepperfry website are handled through Selenium WebDriver.
Page Object Model (POM): The framework follows the Page Object Model to maintain the separation of concerns, with separate classes for each page of the website.
Locator Management: XPath locators for UI elements are stored externally in a Locators.prop file for easy maintainability and reuse across different test classes.
Common Test Cases
Login Functionality: Test cases to verify both positive and negative login scenarios.
Cart Functionality: Verify the ability to add/remove products from the cart, check the cart’s contents, and validate prices.
Wishlist Functionality: Automate the process of adding/removing products from the wishlist.
Product Search: Validate the search functionality for multiple product categories.
Cross-Browser Testing: Run tests across multiple browsers for compatibility verification.
Report Generation
The framework is integrated with Allure to generate detailed and interactive test reports. The reports contain information such as:

Test case execution status (Pass/Fail)
Error logs for failed tests
Screenshots captured during test failures
Execution time for each test case
To generate Allure reports, run the following command:

bash
Copy code
mvn allure:serve
Excluded Features
Karate API Automation: The current framework does not include any API testing with Karate. It is limited to UI automation testing for the Pepperfry website.
Contribution
If you wish to contribute to the project, please fork the repository, make changes, and submit a pull request with a description of the improvements.

License
Author- Sumit Pande
