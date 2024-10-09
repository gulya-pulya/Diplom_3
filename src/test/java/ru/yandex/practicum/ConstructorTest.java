package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.browser.Browser;
import ru.yandex.practicum.pageobject.ConstructorPage;

public class ConstructorTest {

    private final WebDriver driver = Browser.getWebDriverFromEnv();

    @Before
    public void init() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Constructor page should scroll to bun section")
    public void testConstructorPageShouldScrollToBunSection() throws InterruptedException {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();

        checkBunSectionIsActive(constructorPage);
    }

    @Test
    @DisplayName("Constructor page should scroll to sauce section")
    public void testConstructorPageShouldScrollToSauceSection() throws InterruptedException {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();

        constructorPage.clickOnSauceSection();

        checkSauceSectionIsActive(constructorPage);
    }

    @Test
    @DisplayName("Constructor page should scroll to filling section")
    public void testConstructorPageShouldScrollToFillingSection() throws InterruptedException {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitPageLoad();

        constructorPage.clickOnFillingSection();

        checkFillingSectionIsActive(constructorPage);
    }

    @Step("Check bun section is active")
    public void checkBunSectionIsActive(ConstructorPage constructorPage) throws InterruptedException {
        constructorPage.waitBunSectionActive();
    }

    @Step("Check sauce section is active")
    public void checkSauceSectionIsActive(ConstructorPage constructorPage) throws InterruptedException {
        constructorPage.waitSauceSectionActive();
    }

    @Step("Check filling section is active")
    public void checkFillingSectionIsActive(ConstructorPage constructorPage) throws InterruptedException {
        constructorPage.waitFillingSectionActive();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
