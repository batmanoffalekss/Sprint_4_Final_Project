package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.StatusPage;

// объявляем класс
public class ScooterTests {

    // (объявляем driver полем класса) заменили на инициализацию фабрики добавили аннотацию правило
    @Rule
    public DriverFactory factory = new DriverFactory();

    //создаем тест проверки номер заказа не найден
    @Test
    public void testNonExistingOrderNotFound() {

        // получаем драйвер через геттер с фабрики
        WebDriver driver = factory.getDriver();
        //для работы mainPage нужен свой драйвер
        var mainPage = new MainPage(driver);

        // открываем главную страницу
        mainPage.openMainPage();

        // кликаем на кнопку статус заказа
        mainPage.clickOnStatusButton();

        // вводим поле поиска заказа
        mainPage.enterOrderIn("321");

        // кликаем на кнопку Go
        StatusPage statusPage = mainPage.clickOnGoButton();

        // проверка выпадающей картинки ошибки
        statusPage.checkErrorImage();
    }
}
