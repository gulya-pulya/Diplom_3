package ru.yandex.practicum.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {

    public static WebDriver getWebDriverFromEnv() {
        String browserName = System.getProperty("browser");
        if (browserName.equals("CHROME")) {
            return new ChromeDriver();
        } else if (browserName.equals("YANDEX")) {
            ChromeOptions options=new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            return new ChromeDriver(options);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
