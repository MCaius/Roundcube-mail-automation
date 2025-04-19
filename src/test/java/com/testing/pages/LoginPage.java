package com.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // WebElements initialized via PageFactory
    @FindBy(css = "input[id='user']")
    private WebElement emailInput;

    @FindBy(css = "input[id='pass']")
    private WebElement passwordInput;

    @FindBy(css = "button[id='login_submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        enhancedActions.enhancedClick(loginButton);
    }
}
