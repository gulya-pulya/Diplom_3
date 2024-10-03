package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestorePasswordPage {

    private final WebDriver driver;

    private final By restorePasswordPageTitle = By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    private final By loginLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on login link")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Wait for restore password form load")
    public void waitPageLoad() throws InterruptedException {
        new WebDriverWait(driver, 3)
                .until((val) -> driver.findElement(restorePasswordPageTitle).getText().equals("Восстановление пароля"));
    }
}
