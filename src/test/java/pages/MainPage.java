package pages;

import com.codeborne.selenide.SelenideElement;
import components.*;
import models.testDataModels.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    SelectCityPopUp cityPopUp = new SelectCityPopUp();
    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();
    ProductCardPopup productCardPopup = new ProductCardPopup();
    ComboCardPopup comboCardPopup = new ComboCardPopup();
    CartPopup cartPopupComponent = new CartPopup();
    NewAddressPopup newAddressPopup = new NewAddressPopup();
    PizzeriasPopUp pizzeriasPopUp = new PizzeriasPopUp();

    static final SelenideElement allProductsSection = $("main");
    static final SelenideElement cityButton = $("[data-testid='header__about-slogan-text_link']");
    static final SelenideElement cartButton = $("[data-testid='navigation__cart']");
    static final SelenideElement closeCookiePolicyToastify = $(".cookie-policy-button");
    static final SelenideElement itemsInCartCounter = $("[data-testid='cart-button__quantity']");

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

    public MainPage openProductCardInCategory(String productName, ProductCategory category) throws InterruptedException {
        menu.$$("li").findBy(text(category.getName())).click();
        Thread.sleep(1000);
        $(category.getSelector()).find(byText(productName)).shouldBe(visible);
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

    public MainPage enterDeliveryAddress(DeliveryAddress address) {
        newAddressPopup.enterNewAddress(address);
        return this;
    }

    public MainPage choosePickupAddress(PickupAddress address) throws InterruptedException {
        pizzeriasPopUp.choosePickupAddress(address);
        return this;
    }

    public MainPage openCart () throws InterruptedException {
        $(".popup-fade").should(disappear);
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

    public MainPage checkThatItemsInCartCounterNotEmpty() {
        itemsInCartCounter.should(exist);
        return this;
    }

    public MainPage checkCityInPickupAddress(String city) {
        pizzeriasPopUp.checkCityInPickupAddress(city);
        return this;
    }

    public MainPage closePizzeriasPopup() {
        pizzeriasPopUp.closePopup();
        return this;
    }

    public MainPage closeDeliveryMethodPopup() {
        deliveryMethodPopUp.closePopup();
        return this;
    }

    public MainPage openSelectCityPopup() {
        cityButton.click();
        return this;
    }
}