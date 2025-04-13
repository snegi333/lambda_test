package com.lambdatest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaScriptAlertsPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptAlertsPage.class);
    private static final int DEFAULT_TIMEOUT = 10;
    
    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;
    
    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;
    
    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;
    
    @FindBy(id = "result")
    private WebElement resultText;
    
    private final WebDriverWait wait;
    
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }
    
    // Basic page interactions
    public void clickJSAlertButton() {
        logger.info("Clicking JS Alert button");
        jsAlertButton.click();
    }
    
    public void clickJSConfirmButton() {
        logger.info("Clicking JS Confirm button");
        jsConfirmButton.click();
    }
    
    public void clickJSPromptButton() {
        logger.info("Clicking JS Prompt button");
        jsPromptButton.click();
    }
    
    public String getResultText() {
        logger.info("Getting result text");
        return wait.until(ExpectedConditions.visibilityOf(resultText)).getText();
    }
    
    // Alert handling methods
    public Alert waitForAlert() {
        logger.info("Waiting for alert to be present");
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    public String getAlertText(Alert alert) {
        logger.info("Getting alert text");
        return alert.getText();
    }
    
    public void acceptAlert(Alert alert) {
        logger.info("Accepting alert");
        alert.accept();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }
    
    public void dismissAlert(Alert alert) {
        logger.info("Dismissing alert");
        alert.dismiss();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }
    
    public void sendKeysToAlert(Alert alert, String text) {
        logger.info("Sending keys to alert: {}", text);
        alert.sendKeys(text);
    }
    
    // High-level alert handling methods
    public String handleJSAlert() {
        clickJSAlertButton();
        Alert alert = waitForAlert();
        String alertText = getAlertText(alert);
        acceptAlert(alert);
        wait.until(ExpectedConditions.visibilityOf(resultText));
        return alertText;
    }
    
    public String handleJSConfirm(boolean accept) {
        clickJSConfirmButton();
        Alert alert = waitForAlert();
        String alertText = getAlertText(alert);
        if (accept) {
            acceptAlert(alert);
        } else {
            dismissAlert(alert);
        }
        wait.until(ExpectedConditions.visibilityOf(resultText));
        return alertText;
    }
    
    public String handleJSPrompt(String inputText, boolean accept) {
        clickJSPromptButton();
        Alert alert = waitForAlert();
        String alertText = getAlertText(alert);
        if (inputText != null) {
            sendKeysToAlert(alert, inputText);
        }
        if (accept) {
            acceptAlert(alert);
        } else {
            dismissAlert(alert);
        }
        wait.until(ExpectedConditions.visibilityOf(resultText));
        return alertText;
    }
}