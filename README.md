
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
cd QaAutomation
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

# qa-e2e-automation-testng-framework

## Description
This project is created by **Sumit Pande** for an End-to-End Automation Framework built with **Selenium WebDriver**, **TestNG**, and **Allure Reports**. It is designed to automate functional tests for web applications, ensuring smooth and error-free user experiences. The framework supports integration with **Maven** for dependency management and **GitLab** for continuous integration and version control.

## Badges
[![Build Status](https://git.trendsys.in/QaAutomation/qa-e2e-automation-testng-framework/badges/master/pipeline.svg)](https://git.trendsys.in/QaAutomation/qa-e2e-automation-testng-framework/pipelines)

## Visuals
Include images of the application flow, Allure reports, or sample screenshots here.

## Getting Started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

### Add your files
- [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```bash
cd existing_repo
git remote add origin https://git.trendsys.in/QaAutomation/qa-e2e-automation-testng-framework.git
git branch -M master
git push -uf origin master
```

## Integrate with your tools

- [ ] [Set up project integrations](https://git.trendsys.in/QaAutomation/qa-e2e-automation-testng-framework/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thanks to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README

Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
For support, feel free to create an issue on the GitLab repository, or contact the project maintainer.

## Roadmap
1. Add more test cases for different web applications.
2. Integrate additional reporting tools for enhanced test results analysis.
3. Expand CI/CD pipeline for better automation and deployment.

## Contributing
We welcome contributions! If you'd like to contribute to this project, please fork the repository, make changes, and submit a pull request with a clear explanation of the changes.
Development Guidelines:
Ensure that your code passes all tests.
Please write test cases for new features or fixes.
Follow the existing code style.

## Authors and acknowledgment
### Authors:
- **Sumit Pande** – _QA Engineer at Pepperfry_ – Developed and maintained the automation framework, implemented test cases, and integrated Allure reporting.

### Acknowledgments:
- Thanks to the **Pepperfry QA Team** for their continuous feedback and support.
- Special thanks to the **open-source community** for providing tools like **Selenium**, **TestNG**, **Allure Reports**, and **Maven** that made this project possible.
- Acknowledging the contributions of **GitHub** and **GitLab** for hosting the repositories. 

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.

