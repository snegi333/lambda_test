package com.lambdatest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String baseUrl;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public LoginPage(WebDriver driver, String baseUrl) {
        super(driver);
        this.driver = driver;
        this.baseUrl = baseUrl;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        logInfo("Navigating to login page");
        driver.get(baseUrl + "/login");
        waitForPageLoad();
    }

    public void enterUsername(String username) {
        logInfo("Entering username: " + username);
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        logInfo("Entering password");
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLoginButton() {
        logInfo("Clicking login button");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getFlashMessage() {
        logInfo("Getting flash message");
        return wait.until(ExpectedConditions.visibilityOf(flashMessage)).getText();
    }

    public boolean isLoginSuccessful() {
        String message = getFlashMessage();
        logInfo("Checking login success with message: " + message);
        return message.contains("You logged into a secure area!");
    }

    public boolean isLoginFailed() {
        String message = getFlashMessage();
        logInfo("Checking login failure with message: " + message);
        return message.contains("Your username is invalid!");
    }

    public void login(String username, String password) {
        logInfo("Performing login...");
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logInfo("Login sequence completed");
    }
} 