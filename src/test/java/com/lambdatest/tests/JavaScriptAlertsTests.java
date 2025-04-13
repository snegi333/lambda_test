package com.lambdatest.tests;

import com.lambdatest.base.BaseTest;
import com.lambdatest.pages.JavaScriptAlertsPage;
import com.lambdatest.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptAlertsTests extends BaseTest {
    
    private JavaScriptAlertsPage alertsPage;
    
    @BeforeMethod
    public void setup() {
        super.setup(); // Call parent setup to initialize driver
        alertsPage = TestUtils.initializeJavaScriptAlertsPage(driver);
    }
    
    @Test
    public void testJSAlert() {
        String alertText = alertsPage.handleJSAlert();
        Assert.assertEquals(alertText, "I am a JS Alert", "Alert text mismatch");
        Assert.assertEquals(alertsPage.getResultText(), "You successfully clicked an alert", "Result text mismatch");
    }
    
    @Test
    public void testJSConfirmAccept() {
        String alertText = alertsPage.handleJSConfirm(true);
        Assert.assertEquals(alertText, "I am a JS Confirm", "Alert text mismatch");
        Assert.assertEquals(alertsPage.getResultText(), "You clicked: Ok", "Result text mismatch");
    }
    
    @Test
    public void testJSConfirmDismiss() {
        String alertText = alertsPage.handleJSConfirm(false);
        Assert.assertEquals(alertText, "I am a JS Confirm", "Alert text mismatch");
        Assert.assertEquals(alertsPage.getResultText(), "You clicked: Cancel", "Result text mismatch");
    }
    
    @Test
    public void testJSPromptAccept() {
        String inputText = "Test Prompt Input";
        String alertText = alertsPage.handleJSPrompt(inputText, true);
        Assert.assertEquals(alertText, "I am a JS prompt", "Alert text mismatch");
        Assert.assertEquals(alertsPage.getResultText(), "You entered: " + inputText, "Result text mismatch");
    }
    
    @Test
    public void testJSPromptDismiss() {
        String alertText = alertsPage.handleJSPrompt(null, false);
        Assert.assertEquals(alertText, "I am a JS prompt", "Alert text mismatch");
        Assert.assertEquals(alertsPage.getResultText(), "You entered: null", "Result text mismatch");
    }
}