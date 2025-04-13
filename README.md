# Selenium Test Automation Framework For LambdaTest

A robust Selenium WebDriver based test automation framework using Java, TestNG, and Maven.

## Framework Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── lambdatest/
│               ├── base/
│               │   └── BaseTest.java
│               ├── pages/
│               │   ├── BasePage.java
│               │   ├── LoginPage.java
│               │   └── JavaScriptAlertsPage.java
│               └── utils/
│                   └── ConfigReader.java
└── test/
    ├── java/
    │   └── com/
    │       └── lambdatest/
    │           └── tests/
    │               ├── LoginTests.java
    │               └── JavaScriptAlertsTests.java
    └── resources/
        ├── config.properties
        └── testng.xml
```

## Key Components

### Base Classes
- `BaseTest.java`: Core test class handling WebDriver initialization and cleanup
- `BasePage.java`: Base page object with common web element interactions

### Page Objects
- `LoginPage.java`: Handles login page interactions
- `JavaScriptAlertsPage.java`: Manages JavaScript alerts testing

### Utilities
- `ConfigReader.java`: Manages configuration properties
- `TestUtils.java`: Provides utility methods for test setup

### Test Classes
- `LoginTests.java`: Tests for login functionality
- `JavaScriptAlertsTests.java`: Tests for JavaScript alerts

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox browser

## Setup

1. Clone the repository
2. Update `config.properties` with your test configuration:
   ```properties
   base.url=https://the-internet.herokuapp.com
   valid.username=tomsmith
   valid.password=SuperSecretPassword!
   ```

## Running Tests

1. Run all tests:
   ```bash
   mvn clean test
   ```

2. Run specific test class:
   ```bash
   mvn clean test -Dtest=LoginTests
   ```

3. Run specific test method:
   ```bash
   mvn clean test -Dtest=LoginTests#testSuccessfulLogin
   ```

## Test Configuration

- Test configuration is managed in `testng.xml`
- Browser settings and timeouts are configurable in `config.properties`
- Default timeout is 10 seconds if not specified

## Features

- Page Object Model design pattern
- Configurable test execution
- Robust WebDriver management
- Comprehensive logging
- Automatic screenshot capture on failure
- Parallel test execution support

## Best Practices

1. Use page objects for all web interactions
2. Implement proper wait strategies
3. Follow naming conventions
4. Add meaningful assertions
5. Include proper logging
6. Handle exceptions gracefully

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
