package com.lambdatest.utils;

import com.lambdatest.pages.LoginPage;
import com.lambdatest.pages.JavaScriptAlertsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {
    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    private static final int DEFAULT_TIMEOUT = 10;
    
    public static LoginPage performLogin(WebDriver driver, String baseUrl, String username, String password) {
        LoginPage loginPage = new LoginPage(driver, baseUrl);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        return loginPage;
    }
    
    public static WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }
    
    public static String getPageUrl(String path) {
        return ConfigReader.getBaseUrl() + ConfigReader.getProperty(path);
    }
    
    public static void navigateToPage(WebDriver driver, String path) {
        driver.get(getPageUrl(path));
    }
    
    public static JavaScriptAlertsPage initializeJavaScriptAlertsPage(WebDriver driver) {
        JavaScriptAlertsPage alertsPage = new JavaScriptAlertsPage(driver);
        navigateToPage(driver, "javascript.alerts.path");
        return alertsPage;
    }
} 