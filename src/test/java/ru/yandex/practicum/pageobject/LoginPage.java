package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    private final By loginPageTitle = By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");

    private final By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");

    private final By loginButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Set value for input 'email'")
    public void setEmail(String email) {
        setValue(emailInput, email);
    }

    @Step("Set value for input 'password'")
    public void setPassword(String password) {
        setValue(passwordInput, password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Fill login form")
    public void fillLoginForm(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    @Step("Wait for login form load")
    public void waitPageLoad() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until((val) -> driver.findElement(loginPageTitle).getText().equals("Вход"));
    }

    private void setValue(By selector, String value) {
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(value);
    }
}
