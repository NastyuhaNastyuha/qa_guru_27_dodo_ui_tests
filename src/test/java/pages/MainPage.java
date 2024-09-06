package pages;

import com.codeborne.selenide.SelenideElement;
import data.ProductCategoryEnum;
import models.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement allProductsSection = $("main");
    private final SelenideElement cityButton = $("[data-testid='header__about-slogan-text_link']");
    private final SelenideElement cartButton = $("[data-testid='navigation__cart']");
    private final SelenideElement closeCookiePolicyToastify = $(".cookie-policy-button");
    private final SelenideElement menu = $("nav");

    public MainPage openPage() {
        open("");
        return this;
    }

    public MainPage openPageWithSelectedCity(String city) {
        open("/" + city);
        return this;
    }

    public MainPage closeCookiePolicy() {
        closeCookiePolicyToastify.click();
        return this;
    }

    public MainPage openProductCard(String productName) {
        allProductsSection.find(byText(productName)).click();
        return this;
    }

    public MainPage openProductCardInCategory(String productName, ProductCategoryEnum category) {
        menu.$$("li").findBy(text(category.getName())).click();
//        Thread.sleep(1000);
//        $(category.getSelector()).find(byText(productName)).shouldBe(visible);
        $(category.getSelector()).find(byText(productName)).shouldBe(interactable);
        $(category.getSelector()).find(byText(productName)).click();
        return this;
    }

    public MainPage addProductToCartFromMainPage(SimpleItem item) {
        $("[data-testid='menu__meta-product_" + item.getItemId() + "']").
                $("[data-testid='product__button']").click();
        return this;
    }

    public MainPage openCart() {
        $(".popup-fade").should(disappear);
        cartButton.click();
        return this;
    }

    public MainPage openSelectCityPopup() {
        cityButton.click();
        return this;
    }
}