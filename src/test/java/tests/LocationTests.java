package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.testDataModels.DeliveryAddress;
import models.testDataModels.PickupAddress;
import models.testDataModels.SimpleItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static components.DeliveryMethods.DELIVERY;
import static components.DeliveryMethods.PICKUP;
import static io.qameta.allure.Allure.step;

public class LocationTests {

    MainPage mainPage = new MainPage();
    ClassLoader classLoader = AddToCartTests.class.getClassLoader();

    @Severity(SeverityLevel.NORMAL)
    @Owner("rybinaa")
    @Test
    @DisplayName("Можно поменять город")
    void chooseCityTest() throws Exception {
        String firstCity = "Воронеж";
        String secondCity = "Дубна";

        try (InputStream inputStream = classLoader
                .getResourceAsStream("testData/simpleDefaultProduct.json")) {
            ObjectMapper mapper = new ObjectMapper();
            SimpleItem simpleItem = mapper.readValue(inputStream, SimpleItem.class);

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
                mainPage.selectCity(secondCity)
                        .closeCookiePolicy();
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
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("rybinaa")
    @CsvFileSource(resources = "/testData/deliveryAddresses.csv")
    @ParameterizedTest(name = "Можно ввести адрес доставки с заполнением полей: {0}")
    void enterDeliveryAddressTest(String testName, String city, String address, String entrance, String doorCode,
                                  String floor, String apartment, String comment) throws IOException {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setCity(city);
        deliveryAddress.setAddress(address);
        deliveryAddress.setEntrance(entrance);
        deliveryAddress.setDoorCode(doorCode);
        deliveryAddress.setFloor(floor);
        deliveryAddress.setApartment(apartment);
        deliveryAddress.setComment(comment);

        try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/simpleDefaultProduct.json")) {
                ObjectMapper mapper = new ObjectMapper();
                SimpleItem simpleItem = mapper.readValue(inputStream, SimpleItem.class);

                step("Открыть страницу", () -> {
                    mainPage.openPage();
                });
                step("Выбрать город", () -> {
                    mainPage.selectCity(deliveryAddress.getCity())
                            .closeCookiePolicy();
                });
                step("Добавить простой товар в корзину", () -> {
                    mainPage.addProductToCartFromMainPage(simpleItem);
                });
                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(DELIVERY);
                });
                step("Ввести адрес доставки", () -> {
                    mainPage.enterDeliveryAddress(deliveryAddress);
                });
                step("Проверить, что корзина не пуста", () -> {
                    mainPage.checkThatItemsInCartCounterNotEmpty();
                });

    }
}}
