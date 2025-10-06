package ru.yandex.practicum.tests;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startFireFox();
        } else {
            startChrome();
        }
    }

    private void startChrome() {
        // инициализируем поле driver в методе startChrome
        driver = new ChromeDriver();
        // время ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }

    private void startFireFox() {
        // инициализируем поле driver в методе startFireFox
        driver = new FirefoxDriver();
        // время ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }

    public void before() {
        initDriver();
    }

    public void after() {
        driver.quit();
    }
}
