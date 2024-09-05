package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.DeliveryAddress;
import models.SimpleItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.MainPage;

import static data.DeliveryMethodsEnum.DELIVERY;
import static data.DeliveryMethodsEnum.PICKUP;
import static io.qameta.allure.Allure.step;

@Story("Создание заказа")
@Feature("Добавление адреса заказа")
@DisplayName("Тесты на выбор локации")
public class LocationTests extends TestBase {

    MainPage mainPage = new MainPage();
    ClassLoader classLoader = AddToCartTests.class.getClassLoader();

    @Severity(SeverityLevel.NORMAL)
    @Owner("rybinaa")
    @CsvFileSource(resources = "/testData/twoCityForChange.csv")
    @ParameterizedTest(name = "Можно поменять город")
    @DisplayName("Можно поменять город")
    void chooseCityTest(String firstCity, String secondCity) throws Exception {
        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");

            step("Открыть страницу", () -> {
                mainPage.openPage();
            });

            step("Выбрать город", () -> {
                mainPage.selectCity(firstCity)
                        .closeCookiePolicy();
            });

            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                mainPage.chooseDeliveryMethod(PICKUP);
            });
            step("Проверить наличие выбранного города в адресе пиццерий", () -> {
                mainPage.checkCityInPickupAddress(firstCity);
            });
            step("Закрыть модальные окна выбора адреса доставки и ", () -> {
                mainPage.closePizzeriasPopup()
                        .closeDeliveryMethodPopup();
            });
            step("Открыть модальное окно выбора города", () -> {
                mainPage.openSelectCityPopup();
            });
            step("Выбрать город", () -> {
                mainPage.selectCity(secondCity);
            });
            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                mainPage.chooseDeliveryMethod(PICKUP);
            });
            step("Проверить наличие выбранного города в адресе пиццерий", () -> {
                mainPage.checkCityInPickupAddress(secondCity);
            });
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("rybinaa")
    @CsvFileSource(resources = "/testData/deliveryAddresses.csv")
    @ParameterizedTest(name = "Можно ввести адрес доставки с заполнением полей: {0}")
    @DisplayName("Проверка заполнения адреса доставки")
    void enterDeliveryAddressTest (String testName, String filePath) throws Exception {
        DeliveryAddress address = DeliveryAddress.createDeliveryAddressFromJsonFile(filePath);

        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");

            step("Открыть страницу", () -> {
                mainPage.openPage();
            });
            step("Выбрать город", () -> {
                mainPage.selectCity(address.getCity())
                        .closeCookiePolicy();
            });
            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                mainPage.chooseDeliveryMethod(DELIVERY);
            });
            step("Ввести адрес доставки", () -> {
                mainPage.enterDeliveryAddress(address);
            });
            step("Проверить, что корзина не пуста", () -> {
                mainPage.checkThatItemsInCartCounterNotEmpty();
            });
    }
}