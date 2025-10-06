package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;
import java.util.List;

public class MainPage {

    // ссылка на драйвер для инициализации
    private final WebDriver driver;
    private final WebDriverWait wait;

    // переменные с локаторами
    private final By accordionLocatorQuestions = By.cssSelector("div[id^='accordion__heading-']");
    private final By accordionLocatorAnswers = By.cssSelector("div[id^='accordion__panel-']");
    private final By orderField = By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
    private final By statusButton = By.cssSelector(".Header_Link__1TAG7");
    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    // локатор для кнопки "Заказать" сверху страницы
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    // локатор для кнопки "Заказать" внизу страницы
    private final By bottomOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public void selectOrderButton(String positionOrderButton) {
        By orderButton = positionOrderButton.equalsIgnoreCase("topButton") ? topOrderButton : bottomOrderButton;
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    // метод для локаторов аккордеона
    public void locatorFaq(int index, String expectedQuestionText, String expectedAnswerText) {
        // находим все вопросы
        List<WebElement> questions = driver.findElements(accordionLocatorQuestions);

        // скроллим до нужного вопроса
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questions.get(index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
        String actualQuestionText = question.getText();
        Assert.assertEquals(expectedQuestionText, actualQuestionText);

        // находим все ответы
        List<WebElement> answers = driver.findElements(accordionLocatorAnswers);

        // ждём появления ответа и получаем текст
        WebElement answer = wait.until(ExpectedConditions.visibilityOf(answers.get(index)));
        String actualAnswerText = answer.getText();

        // проверяем совпадение текста
        Assert.assertEquals(expectedAnswerText, actualAnswerText);
    }

    // конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    // метод нажать на кнопку Go
    public StatusPage clickOnGoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(goButton));
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }

    // метод ввода номера заказа
    public void enterOrderIn(String orderNumber) {
        driver.findElement(orderField).sendKeys(orderNumber);
    }

    // метод кликнуть на кнопку "Статус заказа"
    public void clickOnStatusButton() {
        driver.findElement(statusButton).click();
    }

    // метод для открытия стартовой страницы
    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    // метод принятия куки
    public void clickCookie() {
        driver.findElement(cookieButton).click();
    }
}
