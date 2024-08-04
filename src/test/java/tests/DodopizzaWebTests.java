package tests;

import com.codeborne.selenide.Condition;
import components.DeliveryMethodPopUp;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import components.SelectCityPopUp;
import testData.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static components.DeliveryMethods.DELIVERY;
import static components.DeliveryMethods.PICKUP;
import static io.qameta.allure.Allure.*;

@Story("Создание заказа")
@Feature("Добавление товара в корзину")
public class DodopizzaWebTests extends TestBase {

    MainPage mainPage = new MainPage();
//    SelectCityPopUp cityPopUp = new SelectCityPopUp();
//    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();
//    @Test
//    void test() {
//        open("https://dodopizza.ru/");
//        $("[data-testid='locality-selector-popup__search-input']").click();
//        $("[data-testid='locality-selector-popup__search-input']").setValue("омск");
//        $$(".locality-selector-popup__table__group").findBy(Condition.text("Омск")).click();
//        $("[data-testid='menu__meta-product_11eceba2b8864f6e96179b0606a94a70']").click();
//        $(".scroll__view").find(withText("тесто")).parent().sibling(0)
//                .find(withTextCaseInsensitive("цыпленок")).click();
//
//        //$("[data-testid='header__about-slogan-text_link']").shouldHave(Condition.text("Омск"));
//    }

    @DisplayName("Простой товар из блока \"Часто заказывают\" можно добавить в корзину. " +
            "Способ получения -- забрать из пиццерии")
    @Test
    void simpleItemsShouldBeAddedToCartFromMainPage() {
        //добавить заполнение данных через файл, туда унести Омск, адрес и Додстер
        String city = "Омск";
        String address = "Ленина, 17";
        String itemName = "Додстер";

        SimpleItem simpleItem = new SimpleItem();
        simpleItem.setItemName("Додстер");
        simpleItem.setItemPrice(189);
        simpleItem.setItemWeight("210");
        simpleItem.setItemId("10d3a240c71be9a11e719be2ab2d427");

        step("Открыть страницу", () -> {
            mainPage.openPage();
        });

        step("Выбрать город", () -> {
            mainPage.selectCity(city)
                    .closeCookiePolicy();
        });
                //.addSimpleOftenOrderedItemToCart("Додстер")

        step("Добавить простой товар из блока \"Часто заказывают\" в корзину", () -> {
            mainPage.addSimpleOftenOrderedItemToCart(simpleItem);
        });
        step("Выбрать способ доставки", () -> {
            mainPage.chooseDeliveryMethod(PICKUP);
        });
        step("Выбрать адрес самовывоза", () -> {
            mainPage.choosePickupAddress(address);
        });
        step("Убедиться, что товар добавлен в корзину", () -> {
            mainPage.checkThatItemInCart(simpleItem);
        });

         //выбираем рандомную категорию
//                .clickCategoryInMenu("Десерты")
//                //выбираем рандомный продукт из тех, у которых кнопка называется "В корзину"
//                //хотя по идее лучше сделать какие-нибудь структуры, чтобы цены в них хранились и можно было проверять сумму в корзине
//                .addItemFromCategoryToCart(category, item)
//                .openCart
//                .checkItemsInCart(oftenOrderedItem, item);
    }

    @DisplayName("Комбо товар можно добавить в корзину с главной страницы. " +
            "Способ получения -- доставка")
    @Test
    void comboItemsShouldBeAddedToCartFromMainPage() {
        String city = "Омск";
        String address = "омск дзержинского 1";
        String entrance = "1";
        String doorCode = "2";
        String floor = "13";
        String apartment = "123";
        String comment = "При входе в подъезд поздоровайтесь с тётей Зиной";



        ComboItem comboItem = new ComboItem();
        comboItem.setComboName("Комбо Завтрак на двоих");
        comboItem.setComboPrice(549);

        SimpleItem comboDish_1 = new SimpleItem();
        comboDish_1.setItemName("Омлет с беконом");
        comboDish_1.setItemWeight("130");

        SimpleItem comboDish_2 = new SimpleItem();
        comboDish_2.setItemName("Омлет с пепперони");
        comboDish_2.setItemWeight("110");

        SimpleItem comboDrink_1 = new SimpleItem();
        comboDrink_1.setItemName("Добрый Кола без сахара");

        AdditiveItem comboDrinkAdditive = new AdditiveItem();
        comboDrinkAdditive.setItemName("Сироп со вкусом фундука");
        comboDrinkAdditive.setItemSurcharge(20);

        ArrayList<AdditiveItem> comboDrinkAdditives = new ArrayList<AdditiveItem>();
        comboDrinkAdditives.add(comboDrinkAdditive);

        SimpleItem comboDrink_2 = new SimpleItem();
        comboDrink_2.setItemName("Кофе Капучино");
        comboDrink_2.setAdditiveItems(comboDrinkAdditives);

        comboItem.setDish_1(comboDish_1);
        comboItem.setDish_2(comboDish_2);
        comboItem.setDrink_1(comboDrink_1);
        comboItem.setDrink_2(comboDrink_2);

        //Комбо завтрак на двоих
        //product id 11ed6428b0626f2f040d14b3b1b9d9d0
        step("Открыть страницу", () -> {
            mainPage.openPage();
        });

        step("Выбрать город", () -> {
            mainPage.selectCity(city)
                    .closeCookiePolicy();
        });
        step("Выбрать категорию в меню", () -> {
            mainPage.goToCategoryFromMenu("Комбо");
        });
        step("Добавить комбо-товар в корзину", () -> {
            mainPage.addComboItemToCart(comboItem);
        });
        step("Выбрать способ доставки", () -> {
            mainPage.chooseDeliveryMethod(DELIVERY);
        });
        step("Ввести адрес доставки", () -> {
            mainPage.enterPickupAddress(address, entrance, doorCode, floor, apartment, comment);
        });
        step("Проверить, что комбо-товар добавлен в корзину", () -> {
            mainPage.checkThatComboItemInCart(comboItem);
        });
    }

    @DisplayName("Пиццу с модификаторами можно добавить в корзину с главной страницы. " +
            "Способ получения -- доставка")
    @Test
    void itemsWithModifiersShouldBeAddedToCartFromMainPage() {
        String city = "Омск";
        String address = "омск дзержинского 1";
        String entrance = "1";
        String doorCode = "2";
        String floor = "13";
        String apartment = "123";
        String comment = "При входе в подъезд поздоровайтесь с тётей Зиной";

        PizzaItem pizzaItem = new PizzaItem();
        pizzaItem.setPizzaName("Пицца Жюльен");
        pizzaItem.setPizzaSize(PizzaSize.BIG);
        pizzaItem.setPizzaPrice(899);
        pizzaItem.setDough("Тонкое");

        AdditiveItem excludedItem_1 = new AdditiveItem();
        excludedItem_1.setItemName("красный лук");

        AdditiveItem excludedItem_2 = new AdditiveItem();
        excludedItem_2.setItemName("чеснок");

        ArrayList<AdditiveItem> excludedItems = new ArrayList<>();
        excludedItems.add(excludedItem_1);
        excludedItems.add(excludedItem_2);
        pizzaItem.setExcludedItems(excludedItems);

        AdditiveItem additiveItem = new AdditiveItem();
        additiveItem.setItemName("Острый перец халапеньо");
        additiveItem.setItemSurcharge(79);

        ArrayList<AdditiveItem> additiveItems = new ArrayList<>();
        additiveItems.add(additiveItem);
        pizzaItem.setAdditiveItems(additiveItems);


        step("Открыть страницу", () -> {
            mainPage.openPage();
        });

        step("Выбрать город", () -> {
            mainPage.selectCity(city)
                    .closeCookiePolicy();
        });

        step("Выбрать категорию в меню", () -> {
            mainPage.goToCategoryFromMenu("Пиццы");
        });

        step("Добавить пиццу в корзину", () -> {
            mainPage.addPizzaToCart(pizzaItem);
        });
        step("Выбрать способ доставки", () -> {
            mainPage.chooseDeliveryMethod(DELIVERY);
        });
        step("Ввести адрес доставки", () -> {
            mainPage.enterPickupAddress(address, entrance, doorCode, floor, apartment, comment);
        });
        step("Проверить, что пицца добавлена в корзину", () -> {
           mainPage.checkThatPizzaItemInCart(pizzaItem);
        });

    }

    void itemsShouldBeAddedInCart() {

    }

    @DisplayName("Прямая трансляция должна быть доступна для просмотра")
    @Test
    void liveVideoShouldBeAvailable() {

    }

    void aboutUsPageShouldBeAvailable() {

    }

    void contactsPageShouldBeAvailable() {

    }

    @DisplayName("Можно изменить ранее выбранный город")
    @Test
    void shouldChangeCity() {

    }
}
