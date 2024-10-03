package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {

    private final WebDriver driver;

    private final By constructorPageTitle = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");

    private final By accountLink = By.xpath("//*[@id=\"root\"]/div/header/nav/a");

    private final By loginLink = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");

    private final By bunSectionTitle = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");

    private final By bunSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");

    private final By sauceSectionTitle = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");

    private final By sauceSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");

    private final By fillingSectionTitle = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");

    private final By fillingSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on account link")
    public void clickOnAccountLink() {
        driver.findElement(accountLink).click();
    }

    @Step("Click on login link")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Click on bun section")
    public void clickOnBunSection() {
        driver.findElement(bunSection).click();
    }

    @Step("Click on sauce section")
    public void clickOnSauceSection() {
        driver.findElement(sauceSection).click();
    }

    @Step("Click on filling section")
    public void clickOnFillingSection() {
        driver.findElement(fillingSection).click();
    }

    @Step("Wait for bun section scroll")
    public void waitBunSectionActive() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(bunSectionTitle));
    }

    @Step("Wait for sauce section scroll")
    public void waitSauceSectionActive() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
    }

    @Step("Wait for bun filling scroll")
    public void waitFillingSectionActive() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
    }

    @Step("Wait for constructor form load")
    public void waitPageLoad() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until((val) -> driver.findElement(constructorPageTitle).getText().equals("Соберите бургер"));
    }
}
