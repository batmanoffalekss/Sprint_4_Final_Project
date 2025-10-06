package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.OrderFormPage;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private final String positionButton;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final boolean rentalDrop;
    private final boolean colorScooter;
    private final String comment;

    public ScooterOrderTest(String positionButton, String name, String lastName, String address, String metro, String phone, String date, boolean rentalDrop, boolean colorScooter, String comment) {
        this.positionButton = positionButton;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalDrop = rentalDrop;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters(name = "Точка входа в сценарий-кнопка: {0}")
    public static Object[][] data() {
        return new Object[][]{
                {"topButton", "Федор", "Сумкин", "Ширяево", "Владыкино", "89991234567", "15", true, false, "Я отнесу кольцо в ломбард."},
                {"bottomButton", "Пендальф", "Серый", "Ревеней", "Нагорная", "89981234567", "25", false, true, "Тикайте хлопцы. Айл би бэк."},
        };
    }

    @Test
    public void scooterOrderTest() {
//получаем драйвер через геттер с фабрики
        WebDriver driver = factory.getDriver();
        // для работы mainPage ей нужен свой драйвер
        var mainPage = new MainPage(driver);
        // открываем главную страницу
        mainPage.openMainPage();
        mainPage.clickCookie();
        // кликнуть по кнопке "Заказать" вверху страницы
        mainPage.selectOrderButton(positionButton);
        var orderFormPage = new OrderFormPage(driver);
        orderFormPage.megaMethod(name, lastName, address, metro, phone, date, rentalDrop, colorScooter, comment);
    }
}
