package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.browser.Browser;
import ru.yandex.practicum.dto.request.UserCreateRequest;
import ru.yandex.practicum.dto.request.UserLoginRequest;
import ru.yandex.practicum.pageobject.ConstructorPage;
import ru.yandex.practicum.pageobject.LoginPage;
import ru.yandex.practicum.pageobject.RegistrationPage;
import ru.yandex.practicum.pageobject.RestorePasswordPage;
import ru.yandex.practicum.util.UserUtils;

import java.util.UUID;

public class LoginTest {

    private final WebDriver driver = Browser.getWebDriverFromEnv();

    private final String email = UUID.randomUUID() + "@ya.ru";
    private final String password = "123456";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserUtils.createUser(new UserCreateRequest(email, email, password));
    }

    @Test
    @DisplayName("Successfully login from account link")
    public void testSuccessfullyLoginFromAccountLink() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site");

        String constructorUrl = "https://stellarburgers.nomoreparties.site/";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();

        checkUrlChange(constructorUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Successfully login from login link")
    public void testSuccessfullyRegistrationFromLoginLink() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site");

        String constructorUrl = "https://stellarburgers.nomoreparties.site/";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();

        checkUrlChange(constructorUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Successfully login from login link on restore password page")
    public void testSuccessfullyRegistrationFromLoginLinkOnRestorePasswordPage() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        String constructorUrl = "https://stellarburgers.nomoreparties.site/";

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.clickOnLoginLink();

        ConstructorPage constructorPage = new ConstructorPage(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();

        checkUrlChange(constructorUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Successfully login from login link on registration page")
    public void testSuccessfullyLoginFromLoginLinkOnRegistrationPage() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        String constructorUrl = "https://stellarburgers.nomoreparties.site/";

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickOnLoginLink();

        ConstructorPage constructorPage = new ConstructorPage(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();

        checkUrlChange(constructorUrl, driver.getCurrentUrl());
    }

    @Step("Check url change")
    public void checkUrlChange(String expectedUrl, String actualUrl) {
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void teardown() {
        UserUtils.deleteUser(new UserLoginRequest(email, password));
        driver.quit();
    }
}
