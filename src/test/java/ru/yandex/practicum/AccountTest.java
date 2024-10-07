package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.browser.Browser;
import ru.yandex.practicum.dto.request.UserCreateRequest;
import ru.yandex.practicum.dto.request.UserLoginRequest;
import ru.yandex.practicum.pageobject.AccountPage;
import ru.yandex.practicum.pageobject.ConstructorPage;
import ru.yandex.practicum.pageobject.LoginPage;
import ru.yandex.practicum.util.UserUtils;

import java.util.UUID;

public class AccountTest {

    private final WebDriver driver = Browser.getWebDriverFromEnv();

    private final String name = UUID.randomUUID().toString();
    private final String email = UUID.randomUUID() + "@ya.ru";
    private final String password = "123456";

    @Before
    public void initialize() {
        driver.get("https://stellarburgers.nomoreparties.site");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserUtils.createUser(new UserCreateRequest(name, email, password));
    }

    @Test
    @DisplayName("Account link should show login form if user don't login")
    public void testAccountLinkShouldShowLoginFormIfUserDontLogin() throws InterruptedException {
        String loginPageUrl = "https://stellarburgers.nomoreparties.site/login";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        checkUrlChange(loginPageUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Account link should show account page if user login")
    public void testAccountLinkShouldShowAccountPageIfUserLogin() throws InterruptedException {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitPageLoad();

        checkAccountFields(accountPage);
    }

    @Test
    @DisplayName("User should successfully logout")
    public void testUserShouldSuccessfullyLogout() throws InterruptedException {
        String loginPageUrl = "https://stellarburgers.nomoreparties.site/login";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitPageLoad();

        accountPage.clickOnLogoutButton();
        loginPage.waitPageLoad();

        checkUrlChange(loginPageUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("User should open constructor page from account page")
    public void testUserShouldOpenConstructorPageFromAccountPage() throws InterruptedException {
        String constructorPageUrl = "https://stellarburgers.nomoreparties.site/";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitPageLoad();

        accountPage.clickOnConstructorLink();
        constructorPage.waitPageLoad();

        checkUrlChange(constructorPageUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("User should open constructor page from account page on logo click")
    public void testUserShouldOpenConstructorPageFromAccountPageOnLogoClick() throws InterruptedException {
        String constructorPageUrl = "https://stellarburgers.nomoreparties.site/";

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitPageLoad();

        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        constructorPage.waitPageLoad();
        constructorPage.clickOnAccountLink();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitPageLoad();

        accountPage.clickOnLogoLink();
        constructorPage.waitPageLoad();

        checkUrlChange(constructorPageUrl, driver.getCurrentUrl());
    }

    @Step("Check account page user fields")
    private void checkAccountFields(AccountPage accountPage) {
        Assert.assertEquals(name, accountPage.getNameInputText());
        Assert.assertEquals(email, accountPage.getEmailInputText());
        Assert.assertNotNull(accountPage.getMaskedPasswordInputText());
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
