package pages.components;

import com.codeborne.selenide.ElementsCollection;
import models.AdditiveItem;
import models.PizzaItem;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CartPopupPizza {
    private final ElementsCollection productCardsInCart = $(".scroll__view").$$("article");

    public CartPopupPizza checkPizzaDough(String pizzaName, String pizzaDough) {
        productCardsInCart.findBy(text(pizzaName)).closest("section")
                .shouldHave(text(pizzaDough));
        return this;
    }

    public CartPopupPizza checkPizzaSize(String pizzaName, String pizzaSize) {
        productCardsInCart.findBy(text(pizzaName)).closest("section")
                .shouldHave(text(pizzaSize));
        return this;
    }

    public CartPopupPizza checkPizzaAddedIngredients(PizzaItem pizzaItem) {
        for (AdditiveItem additiveItem : pizzaItem.getAdditiveItems()) {
            productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .find(withText("+"))
                    .shouldHave(text(additiveItem.getItemName()));
        }
        return this;
    }

    public CartPopupPizza checkPizzaExcludedIngredients(PizzaItem pizzaItem) {
        for (AdditiveItem excludedItem : pizzaItem.getExcludedItems()) {
            productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .find(withText("−"))
                    .shouldHave(text(excludedItem.getItemName()));
        }
        return this;
    }
}
