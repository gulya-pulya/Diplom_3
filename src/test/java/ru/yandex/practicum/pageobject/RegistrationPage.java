package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private final WebDriver driver;

    private final By registrationPageTitle = By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    private final By nameInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");

    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");

    private final By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");

    private final By registrationButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");

    private final By errorText = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");

    private final By loginLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Set value for input 'name'")
    public void setName(String name) {
        setValue(nameInput, name);
    }

    @Step("Set value for input 'email'")
    public void setEmail(String email) {
        setValue(emailInput, email);
    }

    @Step("Set value for input 'password'")
    public void setPassword(String password) {
        setValue(passwordInput, password);
    }

    @Step("Click registration button")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Get error registration text")
    public String getErrorText() {
        return driver.findElement(errorText).getText();
    }

    @Step("Fill registration form")
    public void fillRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    @Step("Click on login link")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Wait for registration form load")
    public void waitPageLoad() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until((val) -> driver.findElement(registrationPageTitle).getText().equals("Регистрация"));
    }

    private void setValue(By selector, String value) {
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(value);
    }
}
