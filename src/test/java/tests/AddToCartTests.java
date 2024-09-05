package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

                step("Открыть страницу", () -> {
                    mainPage.openPageWithSelectedCity(address.getCityForUrl())
                            .closeCookiePolicy();
                });
                step("Открыть карточку товара", () -> {
                    mainPage.openProductCard(simpleItem.getItemName());
                });
                step("Добавить простой товар в корзину", () -> {
                    productCardPopup.addProductToCard();
                });
                step("Выбрать способ доставки", () -> {
                    mainPage.chooseDeliveryMethod(PICKUP);
                });
                step("Выбрать адрес самовывоза", () -> {
                    pizzeriasPopUp.choosePickupAddress(address);
                });
                step("Открыть корзину", () -> {
                    mainPage.openCart();
                });
                step("Проверить, что товар добавлен в корзину", () -> {
                    cartPopupCommon.checkItemName(simpleItem.getItemName())
                            .checkItemPrice(simpleItem.getItemName(), simpleItem.getItemPrice());
                });
    }

    @DisplayName("Простой товар можно добавить в корзину  с главной страницы. " +
            "Способ получения - забрать из пиццерии")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void simpleItemShouldBeAddedToCartFromMainPage() throws Exception {
        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

//                step("Открыть страницу", () -> {
//                    mainPage.openPage();
//                });
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl())
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

    @DisplayName("Пиццу с дефолтными модификаторами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithDefaultModifiersShouldBeAddedToCart() throws Exception {
        PizzaItem pizzaItem = PizzaItem.createPizzaItemFromJsonFile("testData/pizzaWithDefaultIngredients.json");
        DeliveryAddress address = DeliveryAddress.createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

//                step("Открыть страницу", () -> {
//                    mainPage.openPage();
//                });
//
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl())
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

    @DisplayName("Пиццу с модификаторами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithModifiersShouldBeAddedToCart() throws Exception {
        PizzaItem pizzaItem = PizzaItem.createPizzaItemFromJsonFile("testData/pizzaWithChangedIngredients.json");
        DeliveryAddress address = DeliveryAddress.createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

//                step("Открыть страницу", () -> {
//                    mainPage.openPage();
//                });
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl())
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

    @DisplayName("Комбо товар с дефолтными продуктами можно добавить в корзину с главной страницы. " +
            "Способ получения - самовывоз")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithDefaultProductsShouldBeAddedToCart() throws Exception {
        ComboItem comboItem = ComboItem.createComboItemFromJsonFile("testData/comboWithDefaultProducts.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

//                step("Открыть страницу", () -> {
//                    mainPage.openPage();
//                });
//
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl())
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

    @DisplayName("Комбо товар с измененными продуктами можно добавить в корзину с главной страницы. " +
            "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithModifiedProductsShouldBeAddedToCart_testNewObjectStructure() throws Exception {
        ComboItem comboItem = ComboItem.createComboItemFromJsonFile("testData/combo4ProductsWithModifiedProducts.json");
        DeliveryAddress address = DeliveryAddress.createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

//                step("Открыть страницу", () -> {
//                    mainPage.openPage();
//                });
//                step("Выбрать город", () -> {
//                    mainPage.selectCity(address.getCity())
//                            .closeCookiePolicy();
//                });
        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl())
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