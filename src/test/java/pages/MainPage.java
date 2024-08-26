package pages;

import com.codeborne.selenide.SelenideElement;
import components.*;
import models.testDataModels.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    SelectCityPopUp cityPopUp = new SelectCityPopUp();
    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();
    ProductCardPopup productCardPopup = new ProductCardPopup();
    ComboCardPopup comboCardPopup = new ComboCardPopup();
    CartPopup cartPopupComponent = new CartPopup();

    static final SelenideElement allProductsSection = $("main");
    static final SelenideElement cityButton = $("[data-testid='header__about-slogan-text_link']");
    static final SelenideElement cartButton = $("[data-testid='navigation__cart']");
    static final SelenideElement pizzeriasList = $(".pizzerias-list-wrapper");
    static final SelenideElement submitPickupAddressButton = $("[data-testid='menu_product_select']")
            .parent();
    static final SelenideElement closeCookiePolicyToastify = $(".cookie-policy-button");

    static final SelenideElement addressInput = $("[data-testid='delivery_placeholder_on_input_street']");
    static final SelenideElement addressSuggestion = $("[data-testid='two-line-list']").$("li");
    static final SelenideElement entranceInput = $("[name='porch']");
    static final SelenideElement doorCodeInput = $("[name='doorCode']");
    static final SelenideElement floorInput = $("[name='floor']");
    static final SelenideElement apartmentInput = $("[name='apartment']");
    static final SelenideElement commentInput = $("[name='comment']");
    static final SelenideElement addAddressButton = $("[data-testid='add_or_save_spinner_button']");

    public MainPage openPage() {
        open("https://dodopizza.ru/");
        return this;
    }
    static final SelenideElement menu = $("nav");

    public MainPage closeCookiePolicy() {
        closeCookiePolicyToastify.click();
        return this;
    }

    public MainPage selectCity(String city) {

        cityPopUp.selectCityBySearch(city);
        cityButton.shouldHave(text(city));
        return this;
    }

    public MainPage chooseDeliveryMethod(DeliveryMethods deliveryMethod) {
        if (deliveryMethod == DeliveryMethods.PICKUP) {
            deliveryMethodPopUp.choosePickup();
        }
        else if (deliveryMethod == DeliveryMethods.DELIVERY) {
            deliveryMethodPopUp.chooseDelivery();
        }
        return this;
    }

    public MainPage openProductCard(String productName) {
        allProductsSection.find(byText(productName)).click();
        return this;
    }

    public MainPage openProductCardInCategory(String productName, ProductCategory category) {
        menu.$$("li").findBy(text(category.getName())).click();
        $(category.getSelector()).find(byText(productName)).click();
        return this;
    }

    public MainPage addProductToCartFromMainPage(SimpleItem item) {
        $("[data-testid='menu__meta-product_" + item.getItemId() + "']").
                $("[data-testid='product__button']").click();
        return this;
    }

    public MainPage addProductToCartFromPopup() {
        productCardPopup.addProductToCard();
        return this;
    }

    public MainPage changePizzaDoughAndSize(PizzaItem pizza) {
        productCardPopup.changePizzaDoughAndSize(pizza);
        return this;
    }

    public MainPage removeBaseIngredientsFromPizza(PizzaItem pizza) {
        productCardPopup.removeBaseIngredientsFromPizza(pizza);
        return this;
    }

    public MainPage addIngredientsToPizza(PizzaItem pizza) {
        productCardPopup.chooseAdditiveIngredientsForPizza(pizza);
        return this;
    }

    public MainPage addComboToCartFromPopup() {
        comboCardPopup.addComboToCart();
        return this;
    }

    public MainPage replaceItemInComboWithOrder(ComboItem comboItem, Integer order) {
        for (SimpleItem product : comboItem.getProducts()) {
            if (product.getItemOrderInCombo() == order) {
                Integer price = comboItem.getComboPrice() + comboCardPopup.replaceSimpleItemInCombo(product);
                comboItem.setComboPrice(price);
            }
        }
        return this;
    }

    public MainPage changeItemIngredientsInCombo(ComboItem comboItem, Integer order) {

        for (SimpleItem product : comboItem.getProducts()) {
            if (product.getItemOrderInCombo() == order) {
                Integer price = comboItem.getComboPrice() + comboCardPopup.changeItemIngredientsInCombo(product);
                comboItem.setComboPrice(price);
            }
        }
        return this;
    }

    public MainPage enterPickupAddress_test (DeliveryAddress address) {
        addressInput.click();
        addressInput.setValue(address.getAddress());
        addressSuggestion.shouldBe(visible, Duration.ofSeconds(30));
        addressSuggestion.click();
        entranceInput.shouldBe(visible, Duration.ofSeconds(30));
        entranceInput.click();
        entranceInput.setValue(address.getEntrance());
        doorCodeInput.click();
        doorCodeInput.setValue(address.getDoorCode());
        floorInput.click();
        floorInput.setValue(address.getFloor());
        apartmentInput.click();
        apartmentInput.setValue(address.getApartment());
        commentInput.click();
        commentInput.setValue(address.getComment());
        addAddressButton.click();
        return this;
    }

    public MainPage choosePickupAddress_test(PickupAddress address) throws InterruptedException {
        pizzeriasList.$$(".list-item").findBy(text(address.getAddress())).click();
        Thread.sleep(3000);
        submitPickupAddressButton.click();
        return this;
    }

    public MainPage openCart () throws InterruptedException {
        //cartButton.shouldBe(visible, Duration.ofSeconds(30));
        Thread.sleep(3000);
        cartButton.click();
        return this;
    }

    public MainPage checkThatComboItemInCart_test(ComboItem comboItem) {
        cartPopupComponent.checkThatComboItemInCart_test(comboItem);
        return this;
    }

    public MainPage checkThatPizzaItemInCart_test(PizzaItem pizzaItem) {
        cartPopupComponent.checkThatPizzaItemInCart_test(pizzaItem);
        return this;
    }

    public MainPage checkThatItemInCart_test(SimpleItem simpleItem) {
        cartPopupComponent.checkThatItemInCart_test(simpleItem);
        return this;
    }
}