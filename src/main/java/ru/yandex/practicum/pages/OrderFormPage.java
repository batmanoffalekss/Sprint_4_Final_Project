package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class OrderFormPage {
    //ссылка на драйвер для инициализации пейджы
    private final WebDriver driver;
    private final WebDriverWait wait;

    //конструктор
    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    //переменные с локаторами
    private final By nameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Имя']");
    private final By lastNameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.cssSelector(".select-search__input[placeholder='* Станция метро']");
    private final By phoneField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By dateField = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private final By rentalDropField = By.cssSelector(".Dropdown-arrow");
    private final By listRental = By.cssSelector(".Dropdown-menu");
    private final By getRental2Field = By.cssSelector(".Dropdown-option:nth-of-type(2)");
    private final By getRental3Field = By.cssSelector(".Dropdown-option:nth-of-type(3)");
    private final By colorBlackCheckBox = By.cssSelector(".Checkbox_Input__14A2w[id='black']");
    private final By colorGreyCheckBox = By.cssSelector(".Checkbox_Input__14A2w[id='grey']");
    private final By commentField = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='Комментарий для курьера']");
    private final By lastOrderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    private final By yesButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    private final By orderBeenPlaced = By.xpath("//div[@class = 'Order_Text__2broi' and text() = 'Номер заказа: ']");

    //метод для заполнения поля имя
    public void setClientName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод для заполнения поля фамилия
    public void setClientLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //метод для заполнения поля адрес
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //метод заполнения поля метро
    public void setMetro(String metro) {

        WebElement clickMetro = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        clickMetro.click();
        clickMetro.sendKeys(metro);

        By weitButton = By.xpath("//div[contains(@class,'Order_Text') and text()='" + metro + "']");
        WebElement weitLocateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(weitButton));
        weitLocateButton.click();
    }

    //метод для заполнения поля телефон
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //метод перехода на вторую страницу заказа
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //метод заполнения поля когда привезти самокат
    public void setDate(String date) {
        WebElement clickData = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        clickData.click();

        By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + date + "']");
        WebElement dateSelect = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dateSelect.click();
    }

    //метод для заполнения срока аренды
    public void setRentalDrop(boolean rentalDrop) {
        driver.findElement(rentalDropField).click();
        wait.until(ExpectedConditions.elementToBeClickable(listRental));

        By clickRental = (rentalDrop) ? getRental2Field : getRental3Field;
        driver.findElement(clickRental).click();
    }

    //метод выбора чекбокса цвет самоката
    public void clickColorScooter(boolean colorScooter) {
        By clickScooterColor = (colorScooter) ? colorBlackCheckBox : colorGreyCheckBox;
        driver.findElement(clickScooterColor).click();
    }

    //метод заполнения поля комментарий для курьера
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //метод перехода к подтверждению заказа
    public void clickLastOrderButton() {
        driver.findElement(lastOrderButton).click();
    }

    public void clickButtonYes() {
        driver.findElement(yesButton).click();
    }

    public void checkConfirmation() {
        Assert.assertTrue(driver.findElement(orderBeenPlaced).isDisplayed());

    }

    public void megaMethod(String name, String lastName, String address, String metro, String phone, String date, boolean rentalDrop, boolean colorScooter, String comment) {
        setClientName(name);
        setClientLastName(lastName);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        clickNextButton();
        setDate(date);
        setRentalDrop(rentalDrop);
        clickColorScooter(colorScooter);
        setComment(comment);
        clickLastOrderButton();
        clickButtonYes();
        checkConfirmation();

    }


}
