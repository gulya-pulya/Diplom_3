package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private final WebDriver driver;

    private final By accountPageTitle = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a");

    private final By nameInput = By.xpath("//*[@id=\"root\"]/div/main/div/div/div/ul/li[1]/div/div/input");

    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/div/div/ul/li[2]/div/div/input");

    private final By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/div/div/ul/li[3]/div/div/input");

    private final By logoutButton = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button");

    private final By constructorLink = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");

    private final By logoLink = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get name input text")
    public String getNameInputText() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    @Step("Get email input text")
    public String getEmailInputText() {
        return driver.findElement(emailInput).getAttribute("value");
    }

    @Step("Get masked password input text")
    public String getMaskedPasswordInputText() {
        return driver.findElement(passwordInput).getAttribute("value");
    }

    @Step("Click on logout button")
    public void clickOnLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Click on constructor link")
    public void clickOnConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Click on logo link")
    public void clickOnLogoLink() {
        driver.findElement(logoLink).click();
    }

    @Step("Wait for account page load")
    public void waitPageLoad() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until((val) -> driver.findElement(accountPageTitle).getText().equals("Профиль"));
    }
}
