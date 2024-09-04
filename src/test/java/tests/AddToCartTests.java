package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.components.*;

import java.io.InputStream;

import static data.DeliveryMethodsEnum.DELIVERY;
import static data.DeliveryMethodsEnum.PICKUP;
import static io.qameta.allure.Allure.*;
import static data.ProductCategoryEnum.COMBO;
import static data.ProductCategoryEnum.PIZZA;

@Story("Создание заказа")
@Feature("Добавление товара в корзину")
@DisplayName("Тесты на добавление товаров в корзину")
public class AddToCartTests extends TestBase {

    MainPage mainPage = new MainPage();
    ClassLoader classLoader = AddToCartTests.class.getClassLoader();
    CartPopupCommon cartPopupCommon = new CartPopupCommon();
    ProductCardPopup productCardPopup = new ProductCardPopup();
    NewAddressPopup newAddressPopup = new NewAddressPopup();
    PizzeriasPopUp pizzeriasPopUp = new PizzeriasPopUp();

    @DisplayName("Простой товар можно добавить в корзину. " +
            "Способ получения - забрать из пиццерии")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.CRITICAL)
    void simpleItemShouldBeAddedToCart() throws Exception {
        //TODO: убрать куда-нибудь, чтобы не в коде теста определялась переменная
        String city = "omsk";
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/pickupAddress.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            PickupAddress address = mapperForAddress.readValue(inputStreamForAddress, PickupAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/simpleDefaultProduct.json")) {
                ObjectMapper mapper = new ObjectMapper();
                SimpleItem simpleItem = mapper.readValue(inputStream, SimpleItem.class);

                step("Открыть страницу", () -> {
                    //mainPage.openPage();
                    mainPage.openPageWithSelectedCity(city)
                            .closeCookiePolicy();
                });
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
                step("Открыть карточку товара", () -> {
                    mainPage.openProductCard(simpleItem.getItemName());
                });
                step("Добавить простой товар в корзину", () -> {
                    //mainPage.addProductToCartFromPopup();
                    productCardPopup.addProductToCard();
                });
                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(PICKUP);
                });
                step("Выбрать адрес самовывоза", () -> {
                    pizzeriasPopUp.choosePickupAddress(address);
                    //mainPage.choosePickupAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });
                step("Проверить, что товар добавлен в корзину", () -> {
                    cartPopupCommon.checkItemName(simpleItem.getItemName())
                            .checkItemPrice(simpleItem.getItemName(), simpleItem.getItemPrice());
                    //mainPage.checkThatItemInCart_test(simpleItem);
                });
            }
        }
    }

    @DisplayName("Простой товар можно добавить в корзину  с главной страницы. " +
            "Способ получения - забрать из пиццерии")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void simpleItemShouldBeAddedToCartFromMainPage() throws Exception {
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/pickupAddress.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            PickupAddress address = mapperForAddress.readValue(inputStreamForAddress, PickupAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/simpleDefaultProduct.json")) {
                ObjectMapper mapper = new ObjectMapper();
                SimpleItem simpleItem = mapper.readValue(inputStream, SimpleItem.class);

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
                    mainPage.chooseDeliveryMethod(PICKUP);
                });
                step("Выбрать адрес самовывоза", () -> {
                    mainPage.choosePickupAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });
                step("Проверить, что товар добавлен в корзину", () -> {
                    mainPage.checkThatItemInCart_test(simpleItem);
                });
            }
        }
    }

    @DisplayName("Пиццу с дефолтными модификаторами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithDefaultModifiersShouldBeAddedToCart() throws Exception {
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/deliveryAddressWithAllFieldsAreFilled.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            DeliveryAddress address = mapperForAddress.readValue(inputStreamForAddress, DeliveryAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/pizzaWithDefaultIngredients.json")) {
                ObjectMapper mapper = new ObjectMapper();
                PizzaItem pizzaItem = mapper.readValue(inputStream, PizzaItem.class);

                step("Открыть страницу", () -> {
                    mainPage.openPage();
                });

                step("Выбрать город", () -> {
                    mainPage.selectCity(address.getCity())
                            .closeCookiePolicy();
                });

                step("Открыть карточку товара", () -> {
                    mainPage.openProductCardInCategory(pizzaItem.getPizzaName(), PIZZA);

                });
                step("Добавить пиццу в корзину", () -> {
                    mainPage.addProductToCartFromPopup();
                });

                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(DELIVERY);
                });
                step("Ввести адрес доставки", () -> {
                    mainPage.enterDeliveryAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });

                step("Проверить, что пицца добавлена в корзину", () -> {
                    mainPage.checkThatPizzaItemInCart_test(pizzaItem);
                });
            }
        }
    }

    @DisplayName("Пиццу с модификаторами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithModifiersShouldBeAddedToCart() throws Exception {
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/deliveryAddressWithAllFieldsAreFilled.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            DeliveryAddress address = mapperForAddress.readValue(inputStreamForAddress, DeliveryAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/pizzaWithChangedIngredients.json")) {
                ObjectMapper mapper = new ObjectMapper();
                PizzaItem pizzaItem = mapper.readValue(inputStream, PizzaItem.class);

                step("Открыть страницу", () -> {
                    mainPage.openPage();
                });
                step("Выбрать город", () -> {
                    mainPage.selectCity(address.getCity())
                            .closeCookiePolicy();
                });
                step("Открыть карточку товара", () -> {
                    mainPage.openProductCardInCategory(pizzaItem.getPizzaName(), PIZZA);
                });
                step("Выбрать тесто и размер пиццы", () -> {
                    mainPage.changePizzaDoughAndSize(pizzaItem);
                });
                step("Исключить базовые ингредиенты из пиццы", () -> {
                    mainPage.removeBaseIngredientsFromPizza(pizzaItem);
                });
                step("Добавить дополнительные ингредиенты в пиццу", () -> {
                    mainPage.addIngredientsToPizza(pizzaItem);
                });
                step("Добавить пиццу в корзину", () -> {
                    mainPage.addProductToCartFromPopup();
                });
                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(DELIVERY);
                });
                step("Ввести адрес доставки", () -> {
                    mainPage.enterDeliveryAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });
                step("Проверить, что пицца добавлена в корзину", () -> {
                    mainPage.checkThatPizzaItemInCart_test(pizzaItem);
                });
            }
        }
    }

    @DisplayName("Комбо товар с дефолтными продуктами можно добавить в корзину с главной страницы. " +
            "Способ получения - самовывоз")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithDefaultProductsShouldBeAddedToCart() throws Exception {
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/pickupAddress.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            PickupAddress address = mapperForAddress.readValue(inputStreamForAddress, PickupAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/comboWithDefaultProducts.json")) {
                ObjectMapper mapper = new ObjectMapper();
                ComboItem comboItem = mapper.readValue(inputStream, ComboItem.class);

                step("Открыть страницу", () -> {
                    mainPage.openPage();
                });

                step("Выбрать город", () -> {
                    mainPage.selectCity(address.getCity())
                            .closeCookiePolicy();
                });

                step("Открыть карточку комбо-товара", () -> {
                    mainPage.openProductCard(comboItem.getComboName());
                });

                step("Добавить комбо-товар в корзину", () -> {
                    mainPage.addComboToCartFromPopup();
                });

                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(PICKUP);
                });
                step("Выбрать адрес самовывоза", () -> {
                    mainPage.choosePickupAddress(address);
                });

                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });

                step("Проверить, что комбо-товар добавлен в корзину", () -> {
                    mainPage.checkThatComboItemInCart_test(comboItem);
                });
            }
        }
    }

    @DisplayName("Комбо товар с измененными продуктами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithModifiedProductsShouldBeAddedToCart_testNewObjectStructure() throws Exception {
        try (InputStream inputStreamForAddress = classLoader
                .getResourceAsStream("testData/deliveryAddressWithAllFieldsAreFilled.json")) {
            ObjectMapper mapperForAddress = new ObjectMapper();
            DeliveryAddress address = mapperForAddress.readValue(inputStreamForAddress, DeliveryAddress.class);

            try (InputStream inputStream = classLoader
                    .getResourceAsStream("testData/combo4ProductsWithModifiedProducts.json")) {
                ObjectMapper mapper = new ObjectMapper();
                ComboItem comboItem = mapper.readValue(inputStream, ComboItem.class);

                step("Открыть страницу", () -> {
                    mainPage.openPage();
                });
                step("Выбрать город", () -> {
                    mainPage.selectCity(address.getCity())
                            .closeCookiePolicy();
                });
                step("Открыть карточку комбо-товара", () -> {
                    mainPage.openProductCardInCategory(comboItem.getComboName(), COMBO);
                });
                step("Заменить второй товар в комбо", () -> {
                    mainPage.replaceItemInComboWithOrder(comboItem, 2);
                });
                step("Заменить третий товар в комбо", () -> {
                    mainPage.replaceItemInComboWithOrder(comboItem, 3);
                });
                step("Изменить состав четвертого товара в комбо", () -> {
                    mainPage.changeItemIngredientsInCombo(comboItem, 4);
                });
                step("Добавить комбо-товар в корзину", () -> {
                    mainPage.addComboToCartFromPopup();
                });
                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(DELIVERY);
                });
                step("Ввести адрес доставки", () -> {
                    mainPage.enterDeliveryAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });
                step("Проверить, что комбо-товар добавлен в корзину", () -> {
                    mainPage.checkThatComboItemInCart_test(comboItem);
                });
            }
        }
    }
}
