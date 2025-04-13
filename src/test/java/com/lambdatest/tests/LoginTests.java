package com.lambdatest.tests;

import com.lambdatest.base.BaseTest;
import com.lambdatest.pages.LoginPage;
import com.lambdatest.utils.ConfigReader;
import com.lambdatest.utils.TestUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Login Tests")
@Feature("User Authentication")
public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @Test(description = "Test successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test successful login with valid credentials")
    @Story("User should be able to login with valid credentials")
    public void testSuccessfulLogin() {
        loginPage = TestUtils.performLogin(
                driver,
                ConfigReader.getBaseUrl(),
                ConfigReader.getValidUsername(),
                ConfigReader.getValidPassword()
        );

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful");
    }

    @Test(description = "Test login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test login with invalid credentials should fail")
    @Story("User should not be able to login with invalid credentials")
    public void testInvalidLogin() {
        loginPage = TestUtils.performLogin(
                driver,
                ConfigReader.getBaseUrl(),
                ConfigReader.getInvalidUsername(),
                ConfigReader.getInvalidPassword()
        );

        Assert.assertTrue(loginPage.isLoginFailed(), "Login should have failed with invalid credentials");
    }
} 