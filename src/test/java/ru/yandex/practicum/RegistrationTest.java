package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.browser.Browser;
import ru.yandex.practicum.pageobject.LoginPage;
import ru.yandex.practicum.pageobject.RegistrationPage;

import java.util.UUID;

public class RegistrationTest {

    private final WebDriver driver = Browser.getWebDriverFromEnv();

    @Test
    @DisplayName("Successfully registration")
    public void testSuccessfullyRegistration() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        String loginPageUrl = "https://stellarburgers.nomoreparties.site/login";

        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID() + "@ya.ru";
        String password = "123456";

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.waitPageLoad();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickRegistrationButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        checkUrlChange(loginPageUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration should show error if password is less than 6 symbol")
    public void testShowErrorOnSmallPassword() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID() + "@ya.ru";
        String password = "12345";

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.waitPageLoad();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickRegistrationButton();

        checkErrorMessage("Некорректный пароль", registrationPage.getErrorText());
    }

    @Step("Check error message")
    public void checkErrorMessage(String expectedErrorMessage, String actualErrorMessage) {
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Step("Check url change")
    public void checkUrlChange(String expectedUrl, String actualUrl) {
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void teardown() {
        //driver.quit();
    }
}
