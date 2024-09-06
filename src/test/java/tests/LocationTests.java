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
import pages.components.*;

import static io.qameta.allure.Allure.step;

@Story("Создание заказа")
@Feature("Добавление адреса заказа")
@DisplayName("Тесты на выбор локации")
public class LocationTests extends TestBase {

    MainPage mainPage = new MainPage();
    SelectCityPopUp selectCityPopUp = new SelectCityPopUp();
    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();
    PizzeriasPopUp pizzeriasPopUp = new PizzeriasPopUp();
    NewAddressPopup newAddressPopup = new NewAddressPopup();
    CartPopupCommon cartPopupCommon = new CartPopupCommon();

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
                selectCityPopUp.selectCityBySearch(firstCity);
                mainPage.closeCookiePolicy();
            });

            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                deliveryMethodPopUp.choosePickup();
            });
            step("Проверить наличие выбранного города в адресе пиццерий", () -> {
                pizzeriasPopUp.checkCityInPickupAddress(firstCity);
            });
            step("Закрыть модальные окна выбора адреса доставки и метода доставки", () -> {
                pizzeriasPopUp.closePopup();
                deliveryMethodPopUp.closePopup();
            });
            step("Открыть модальное окно выбора города", () -> {
                mainPage.openSelectCityPopup();
            });
            step("Выбрать город", () -> {
                selectCityPopUp.selectCityBySearch(secondCity);
            });
            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                deliveryMethodPopUp.choosePickup();
            });
            step("Проверить наличие выбранного города в адресе пиццерий", () -> {
                pizzeriasPopUp.checkCityInPickupAddress(secondCity);
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
            selectCityPopUp.selectCityBySearch(address.getCity());
            mainPage.closeCookiePolicy();
        });
            step("Добавить простой товар в корзину", () -> {
                mainPage.addProductToCartFromMainPage(simpleItem);
            });
            step("Выбрать способ доставки", () -> {
                deliveryMethodPopUp.chooseDelivery();
            });
            step("Ввести адрес доставки", () -> {
                newAddressPopup.enterNewAddress(address);
            });
            step("Проверить, что корзина не пуста", () -> {
                cartPopupCommon.checkThatItemsInCartCounterNotEmpty();
            });
    }
}