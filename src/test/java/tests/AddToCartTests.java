package tests;

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

import static io.qameta.allure.Allure.*;
import static data.ProductCategoryEnum.COMBO;
import static data.ProductCategoryEnum.PIZZA;

@Story("Создание заказа")
@Feature("Добавление товара в корзину")
@DisplayName("Тесты на добавление товаров в корзину")
public class AddToCartTests extends TestBase {

    MainPage mainPage = new MainPage();
    CartPopupCommon cartPopupCommon = new CartPopupCommon();
    ProductCardPopup productCardPopup = new ProductCardPopup();
    ComboCardPopup comboCardPopup = new ComboCardPopup();
    NewAddressPopup newAddressPopup = new NewAddressPopup();
    PizzeriasPopUp pizzeriasPopUp = new PizzeriasPopUp();
    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();
    CartPopupPizza cartPopupPizza = new CartPopupPizza();
    CartPopupCombo cartPopupCombo = new CartPopupCombo();

    @DisplayName("Простой товар можно добавить в корзину. " + "Способ получения - забрать из пиццерии")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.CRITICAL)
    void simpleItemShouldBeAddedToCart() throws Exception {
        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });
        step("Открыть карточку товара", () -> {
            mainPage.openProductCard(simpleItem.getItemName());
        });
        step("Добавить простой товар в корзину", () -> {
            productCardPopup.addProductToCard();
        });
        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.choosePickup();
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

    @DisplayName("Простой товар можно добавить в корзину  с главной страницы. "
            + "Способ получения - забрать из пиццерии")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void simpleItemShouldBeAddedToCartFromMainPage() throws Exception {
        SimpleItem simpleItem = SimpleItem.createSimpleItemFromJsonFile("testData/simpleDefaultProduct.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });
        step("Добавить простой товар в корзину", () -> {
            mainPage.addProductToCartFromMainPage(simpleItem);
        });
        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.choosePickup();
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

    @DisplayName("Пиццу с дефолтными модификаторами можно добавить в корзину с главной страницы. "
            + "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithDefaultModifiersShouldBeAddedToCart() throws Exception {
        PizzaItem pizzaItem = PizzaItem
                .createPizzaItemFromJsonFile("testData/pizzaWithDefaultIngredients.json");
        DeliveryAddress address = DeliveryAddress
                .createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });
        step("Открыть карточку товара", () -> {
            mainPage.openProductCardInCategory(pizzaItem.getPizzaName(), PIZZA);
        });
        step("Добавить пиццу в корзину", () -> {
            productCardPopup.addProductToCard();
        });

        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.chooseDelivery();
        });
        step("Ввести адрес доставки", () -> {
            newAddressPopup.enterNewAddress(address);
        });
        step("Открыть корзину", () -> {
            mainPage.openCart();
        });
        step("Проверить, что пицца добавлена в корзину", () -> {
            cartPopupCommon.checkItemName(pizzaItem.getPizzaName())
                    .checkItemPrice(pizzaItem.getPizzaName(), pizzaItem.getPizzaPrice());
            cartPopupPizza.checkPizzaDough(pizzaItem.getPizzaName(), pizzaItem.getDough())
                    .checkPizzaSize(pizzaItem.getPizzaName(), pizzaItem.getPizzaSize().getSize());
        });
    }

    @DisplayName("Пиццу с модификаторами можно добавить в корзину с главной страницы. " + "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void pizzaWithModifiersShouldBeAddedToCart() throws Exception {
        PizzaItem pizzaItem = PizzaItem
                .createPizzaItemFromJsonFile("testData/pizzaWithChangedIngredients.json");
        DeliveryAddress address = DeliveryAddress
                .createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });
        step("Открыть карточку товара", () -> {
            mainPage.openProductCardInCategory(pizzaItem.getPizzaName(), PIZZA);
        });
        step("Выбрать тесто и размер пиццы", () -> {
            productCardPopup.selectPizzaDoughAndSize(pizzaItem);
        });
        step("Исключить базовые ингредиенты из пиццы", () -> {
            productCardPopup.removeBaseIngredientsFromPizza(pizzaItem);
        });
        step("Добавить дополнительные ингредиенты в пиццу", () -> {
            productCardPopup.chooseAdditiveIngredientsForPizza(pizzaItem);
        });
        step("Добавить пиццу в корзину", () -> {
            productCardPopup.addProductToCard();
        });
        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.chooseDelivery();
        });
        step("Ввести адрес доставки", () -> {
            newAddressPopup.enterNewAddress(address);
        });
        step("Открыть корзину", () -> {
            mainPage.openCart();
        });
        step("Проверить название и цену пиццы в корзине", () -> {
            cartPopupCommon.checkItemName(pizzaItem.getPizzaName())
                    .checkItemPrice(pizzaItem.getPizzaName(), pizzaItem.getPizzaPrice());
        });
        step("Проверить выбранные тесто и размер пиццы", () -> {
            cartPopupPizza.checkPizzaDough(pizzaItem.getPizzaName(), pizzaItem.getDough())
                    .checkPizzaSize(pizzaItem.getPizzaName(), pizzaItem.getPizzaSize().getSize());
        });
        step("Проверить добавленные в пиццу ингредиенты", () -> {
            cartPopupPizza.checkPizzaAddedIngredients(pizzaItem);
        });
        step("Проверить, что из пиццы исключены выбранные ингредиенты", () -> {
            cartPopupPizza.checkPizzaExcludedIngredients(pizzaItem);
        });
    }

    @DisplayName("Комбо товар с дефолтными продуктами можно добавить в корзину с главной страницы. "
            + "Способ получения - самовывоз")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithDefaultProductsShouldBeAddedToCart() throws Exception {
        ComboItem comboItem = ComboItem.createComboItemFromJsonFile("testData/comboWithDefaultProducts.json");
        PickupAddress address = PickupAddress.createPickupAddressFromJsonFile("testData/pickupAddress.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });

        step("Открыть карточку комбо-товара", () -> {
            mainPage.openProductCard(comboItem.getComboName());
        });

        step("Добавить комбо-товар в корзину", () -> {
            comboCardPopup.addComboToCart();
        });

        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.choosePickup();
        });
        step("Выбрать адрес самовывоза", () -> {
            pizzeriasPopUp.choosePickupAddress(address);
        });
        step("Открыть корзину", () -> {
            mainPage.openCart();
        });
        step("Проверить, что комбо-товар добавлен в корзину", () -> {
            cartPopupCommon.checkItemName(comboItem.getComboName())
                    .checkItemPrice(comboItem.getComboName(), comboItem.getComboPrice());
        });
    }

    @DisplayName("Комбо товар с измененными продуктами можно добавить в корзину с главной страницы. " + "Способ получения - доставка")
    @Test
    @Owner("rybinaa")
    @Severity(SeverityLevel.NORMAL)
    void comboItemWithModifiedProductsShouldBeAddedToCart_testNewObjectStructure() throws Exception {
        ComboItem comboItem = ComboItem
                .createComboItemFromJsonFile("testData/combo4ProductsWithModifiedProducts.json");
        DeliveryAddress address = DeliveryAddress
                .createDeliveryAddressFromJsonFile("testData/deliveryAddressWithAllFieldsAreFilled.json");

        step("Открыть страницу", () -> {
            mainPage.openPageWithSelectedCity(address.getCityForUrl()).closeCookiePolicy();
        });
        step("Открыть карточку комбо-товара", () -> {
            mainPage.openProductCardInCategory(comboItem.getComboName(), COMBO);
        });
        step("Заменить второй товар в комбо", () -> {
            comboCardPopup.replaceItemInComboWithOrder(comboItem, 2);
        });
        step("Заменить третий товар в комбо", () -> {
            comboCardPopup.replaceItemInComboWithOrder(comboItem, 3);
        });
        step("Изменить состав четвертого товара в комбо", () -> {
            comboCardPopup.changeItemIngredientsInCombo(comboItem, 4);
        });
        step("Добавить комбо-товар в корзину", () -> {
            comboCardPopup.addComboToCart();
        });
        step("Выбрать способ доставки", () -> {
            deliveryMethodPopUp.chooseDelivery();
        });
        step("Ввести адрес доставки", () -> {
            newAddressPopup.enterNewAddress(address);
        });
        step("Открыть корзину", () -> {
            mainPage.openCart();
        });
        step("Проверить, что комбо-товар добавлен в корзину", () -> {
            cartPopupCommon.checkItemName(comboItem.getComboName())
                    .checkItemPrice(comboItem.getComboName(), comboItem.getComboPrice());
        });
        step("Проверить примененные модификации товаров в комбо", () -> {
            cartPopupCombo.checkSimpleItemsInCombo(comboItem);
        });
    }
}