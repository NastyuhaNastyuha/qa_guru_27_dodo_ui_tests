package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.DeliveryMethodPopUp;
import components.DeliveryMethods;
import components.OftenOrderedSimpleItemCard;
import components.SelectCityPopUp;
import testData.ComboItem;
import testData.PizzaItem;
import testData.PizzaSize;
import testData.SimpleItem;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    SelectCityPopUp cityPopUp = new SelectCityPopUp();
    OftenOrderedSimpleItemCard oftenOrderedSimpleItemCard = new OftenOrderedSimpleItemCard();
    DeliveryMethodPopUp deliveryMethodPopUp = new DeliveryMethodPopUp();

    static final SelenideElement cityButton = $("[data-testid='header__about-slogan-text_link']");
    static final SelenideElement deliveryButton = $("[data-testid='how_to_get_order_delivery_action']");
    static final SelenideElement pickUpButton = $("[data-testid='how_to_get_order_pickup_action']");

    static final SelenideElement toCartButton = $("[data-testid='product__button']");

    //комбо-завтрак на двоих
    static final SelenideElement comboItemCard =
            $("[data-testid='menu__meta-product_11ed6428b0626f2f040d14b3b1b9d9d0']");

    //Сырники со сгущенным молоком
    static final SelenideElement simpleItemCard =
            $("[data-testid='menu__meta-product_11ed5ae1adc4192732bf7de344c27710']");

    //data-testid="product__card-11ea5ee39dcbd9124a66f764ca4d4470"
    //data-testid="menu__meta-product_11ea5ee39dcbd9124a66f764ca4d4470"
    static final SelenideElement pizzaItemCard =
            $("[data-testid='menu__meta-product_11ee788f97bb5f983f3aede9318dff30']");


    //static final SelenideElement oftenOrderedArea = $("[data-testid='popular_title']").parent();

    //додстер
//    static final SelenideElement oftenOrderedSimpleItem = $("[data-testid='popular_title']").parent()
//            .$("article data-index='1'");
    static final SelenideElement oftenOrderedSection = $("[data-testid='popular_title']").parent();

    static final ElementsCollection menuCategory = $$("nav li");
    static final SelenideElement cartButton = $("[data-testid='navigation__cart']");
    //модалка Пиццерии для самовывоза
    static final SelenideElement pizzeriasList = $(".pizzerias-list-wrapper");
    static final SelenideElement submitPickupAddressButton = $("[data-testid='menu_product_select']")
            .parent();

    static final SelenideElement addedToCartToaster = $(".notification-enter-done");
    static final SelenideElement cartPopup = $(".scroll__view");
    static final SelenideElement comboSection = $("#nfjka");
    static final SelenideElement comboPopupListOfItems = $(".slots__view");
    static final SelenideElement comboSecondDish = $("[data-id='11ed6428b0626f2f040d14b3c2780b70']");
    static final SelenideElement comboPopupProductSelector = $(".product-selector-scroll__view");
    static final SelenideElement comboFirstDrink = $("[data-id='11ef080abe20b9a775549b17d7431e50']");
    static final SelenideElement comboSecondDrinkChangeComposition = $("[data-id='11ef080abe20b9a775549b17bdd4a6a0']")
            .$(".change-button");
    static final SelenideElement comboPopupToppingSelector = $(".toppings");
    //static final SelenideElement comboPopupToppingsSaveButton = $(".save-button");
    //static final SelenideElement comboPopupToppingsSaveButton = $("[data-type='primary']").find(byText("Сохранить"));
    //static final SelenideElement comboPopupToppingsSaveButton = $(".ingredients-editor").$("[data-type='primary']");
        //.scrollIntoView(true);
    static final SelenideElement comboPopupToppingsSaveButton = $("button.save-button");


    //static final SelenideElement comboPopupAddButton = $(".add-button");
    //static final SelenideElement comboPopupAddButton = $("[data-type='primary']").find(byText("В корзину"));
    static final SelenideElement comboPopupAddButton = $("button.add-button");
    static final SelenideElement closeCookiePolicyToastify = $(".cookie-policy-button");

    static final SelenideElement addressInput = $("[data-testid='delivery_placeholder_on_input_street']");
    static final SelenideElement addressSuggestion = $("[data-testid='two-line-list']").$("li");
    static final SelenideElement entranceInput = $("[name='porch']");
    static final SelenideElement doorCodeInput = $("[name='doorCode']");
    static final SelenideElement floorInput = $("[name='floor']");
    static final SelenideElement apartmentInput = $("[name='apartment']");
    static final SelenideElement commentInput = $("[name='comment']");
    static final SelenideElement addAddressButton = $("[data-testid='add_or_save_spinner_button']");

    //для пиццы с модификаторами
    static final SelenideElement pizzaSection = $("#guzhy");
    //static final SelenideElement pizzaSizeButton = $()
    static final SelenideElement itemModifyingArea = $(".scroll__view");
    static final SelenideElement pizzaPopupAddToCartButton = $("[data-testid='button_add_to_cart']");

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

    public MainPage addSimpleOftenOrderedItemToCart(SimpleItem simpleItem) {
        //oftenOrderedSimpleItem.click();
        oftenOrderedSection.find(byText(simpleItem.getItemName())).click();
        oftenOrderedSimpleItemCard.addItemToCart(simpleItem.getItemName());
        return this;
    }

    public MainPage clickCategoryInMenu(String category) {
        menuCategory.findBy(text(category)).click();
        //assertThat("").isVisible()
        //проверка что заголовок нужный виден
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

    public MainPage choosePickupAddress(String address) {
        //pizzeriasList.$(".list-item").find(byText("address")).click();
        pizzeriasList.$$(".list-item").findBy(text(address)).click();
        submitPickupAddressButton.click();
        return this;
    }

    public MainPage enterPickupAddress(String address, String entrance, String doorCode, String floor, String apartment, String comment) {
        addressInput.click();
        addressInput.setValue(address);
        addressSuggestion.click();
        entranceInput.click();
        entranceInput.setValue(entrance);
        doorCodeInput.click();
        doorCodeInput.setValue(doorCode);
        floorInput.click();
        floorInput.setValue(floor);
        apartmentInput.click();
        apartmentInput.setValue(apartment);
        commentInput.click();
        commentInput.setValue(comment);
        addAddressButton.click();
        return this;
    }

    public MainPage checkToaster(String itemName) {
        addedToCartToaster.shouldHave(text("Добавлено"));
        addedToCartToaster.shouldHave(text(itemName));
        return this;
    }

    public MainPage checkThatItemInCart(SimpleItem simpleItem) {
        cartButton.click();
        cartPopup.$$("article").findBy(text(simpleItem.getItemName())).shouldHave(text(simpleItem.getItemName()));
        cartPopup.$$("article").findBy(text(simpleItem.getItemName())).$(".current").shouldHave(text((simpleItem.getItemPrice().toString())));
        return this;
    }

    public MainPage checkThatComboItemInCart(ComboItem comboItem) {
        cartButton.click();
        cartPopup.$$("article").findBy(text(comboItem.getComboName())).shouldHave(text(comboItem.getComboName()));
        cartPopup.$$("article").findBy(text(comboItem.getComboName())).$(".current").shouldHave(text((comboItem
                .getComboPrice().toString())));

        return this;
    }

    public MainPage checkThatPizzaItemInCart(PizzaItem pizzaItem) {
        cartButton.click();
        cartPopup.$$("article").findBy(text(pizzaItem.getPizzaName())).shouldHave(text(pizzaItem.getPizzaName()));
        cartPopup.$$("article").findBy(text(pizzaItem.getPizzaName())).$(".current").shouldHave(text((pizzaItem
                .getPizzaPrice().toString())));

        return this;
    }

    //можно просто в итоге каждый менять на пришедший в парамере?
    public MainPage addComboItemToCart(ComboItem comboItem) {
        comboSection.$$("article").findBy(text(comboItem.getComboName())).click();
        comboSecondDish.click();
        //comboPopupProductSelector.$$("div").findBy(text(comboItem.getDish_2().getItemName())).click();

        //comboPopupProductSelector.$$("div").findBy(text("Омлет с пепперони")).click();
        comboPopupProductSelector.$("div").find(byText("Омлет с пепперони")).click();
        comboFirstDrink.click();
        //comboPopupProductSelector.$("div").find(byText(comboItem.getDrink_1().getItemName())).click();
        comboPopupProductSelector.$("div").find(byText("Добрый Кола без сахара")).click();

        comboSecondDrinkChangeComposition.click();
        //comboPopupToppingSelector.$("button").find(byText(comboItem.getDrink_2().getAdditiveItems().get(0)
        //        .getItemName())).click();
        //comboPopupToppingSelector.$("button h2").find(byText("Сироп со вкусом фундука")).click();
        //comboPopupToppingSelector.$("h2.title:contains(Сироп со вкусом фундука)").click();
        comboPopupToppingSelector.$("[alt='Сироп со вкусом фундука']").click();
        Integer price = comboItem.getComboPrice() + comboItem.getDrink_2().getAdditiveItems().get(0).getItemSurcharge();
        comboItem.setComboPrice(price);


        comboPopupToppingsSaveButton.click();
        comboPopupAddButton.click();

        return this;
    }

    public MainPage goToCategoryFromMenu(String category) {
        menu.$$("li").findBy(text(category)).click();
        return this;
    }

    public MainPage addPizzaToCart(PizzaItem pizzaItem) {
        PizzaSize pizzaSize = pizzaItem.getPizzaSize();

        pizzaSection.$$("article").findBy(text(pizzaItem.getPizzaName())).click();
        $(pizzaSize.getSelector()).click();
        itemModifyingArea.find(byText(pizzaItem.getDough())).click();
        itemModifyingArea.find(withText("тесто")).parent().sibling(0)
                .find(withTextCaseInsensitive(pizzaItem.getExcludedItems().get(0).getItemName())).click();
        itemModifyingArea.find(withText("тесто")).parent().sibling(0)
                .find(withTextCaseInsensitive(pizzaItem.getExcludedItems().get(1).getItemName())).click();

        itemModifyingArea.find(byText("Добавить по вкусу")).sibling(0)
                .$$("button").findBy(text(pizzaItem.getAdditiveItems().get(0)
                        .getItemName())).click();
        Integer price = pizzaItem.getPizzaPrice() + pizzaItem.getAdditiveItems().get(0)
                .getItemSurcharge();
        pizzaItem.setPizzaPrice(price);

        pizzaPopupAddToCartButton.click();

        return this;
    }

//    public MainPage addSimpleItemFromCategoryToCart() {
//        simpleItemCard.
//        return this;
//    }
}