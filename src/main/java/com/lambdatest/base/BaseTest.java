package com.lambdatest.base;

import com.lambdatest.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10; // Default timeout in seconds

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite - Setting up test environment");
        // Set up WebDriverManager once for all tests
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test - Setting up test class");
    }

    @BeforeMethod
    public void setup() {
        System.out.println("Before Method - Setting up WebDriver");
        initializeWebDriver();
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After Method - Cleaning up WebDriver");
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
                System.out.println("WebDriver successfully closed");
            }
        } catch (Exception e) {
            System.err.println("Error while closing WebDriver: " + e.getMessage());
        }
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test - Cleaning up test class");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite - Cleaning up test environment");
    }

    private void initializeWebDriver() {
        try {
            System.out.println("Initializing WebDriver...");
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--remote-allow-origins=*"
            );

            boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
            if (isHeadless) {
                System.out.println("Running in headless mode");
                options.addArguments("--headless");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            int timeout = getTimeout();
            System.out.println("Setting implicit wait timeout to " + timeout + " seconds");
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            System.out.println("WebDriver initialized successfully");
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
            throw e;
        }
    }

    private int getTimeout() {
        String timeoutStr = ConfigReader.getProperty("timeout");
        if (timeoutStr == null || timeoutStr.isEmpty()) {
            System.out.println("No timeout specified in config, using default: " + DEFAULT_TIMEOUT);
            return DEFAULT_TIMEOUT;
        }
        try {
            int timeout = Integer.parseInt(timeoutStr);
            System.out.println("Using configured timeout: " + timeout);
            return timeout;
        } catch (NumberFormatException e) {
            System.out.println("Invalid timeout in config, using default: " + DEFAULT_TIMEOUT);
            return DEFAULT_TIMEOUT;
        }
    }
} 