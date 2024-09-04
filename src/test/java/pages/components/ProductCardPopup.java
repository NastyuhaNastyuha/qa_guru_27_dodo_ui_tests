package pages.components;

import com.codeborne.selenide.SelenideElement;
import models.AdditiveItem;
import models.PizzaItem;
import data.PizzaSizeEnum;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductCardPopup {
    private final SelenideElement addProductToCartButton = $("[data-testid='button_add_to_cart']");
    private final SelenideElement pizzaModifyingArea = $(".scroll__view");

    public void addProductToCard() {
        addProductToCartButton.click();
    }

    public void selectPizzaDoughAndSize(PizzaItem pizza) {
        PizzaSizeEnum pizzaSizeEnum = pizza.getPizzaSizeEnum();
        pizzaModifyingArea.find(byText(pizza.getDough())).click();
        $(pizzaSizeEnum.getSelector()).click();
    }

    public void removeBaseIngredientsFromPizza(PizzaItem pizza) {
        for (AdditiveItem ingredient : pizza.getExcludedItems()) {
            pizzaModifyingArea.find(withText("тесто")).parent().sibling(0)
                    .find(withTextCaseInsensitive(ingredient.getItemName())).click();

        }
    }

    public void chooseAdditiveIngredientsForPizza(PizzaItem pizza) {
        Integer price = pizza.getPizzaPrice();
        for (AdditiveItem ingredient : pizza.getAdditiveItems()) {
            pizzaModifyingArea.find(byText("Добавить по вкусу")).sibling(0)
                    .$$("button").findBy(text(ingredient.getItemName())).click();
            price = price + ingredient.getItemSurcharge();
        }
        pizza.setPizzaPrice(price);
    }
}