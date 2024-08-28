package components;

import com.codeborne.selenide.SelenideElement;
import models.testDataModels.AdditiveItem;
import models.testDataModels.PizzaItem;
import models.testDataModels.PizzaSize;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductCardPopup {
    static final SelenideElement addProductToCartButton = $("[data-testid='button_add_to_cart']");
    static final SelenideElement pizzaModifyingArea = $(".scroll__view");

    public void addProductToCard() {
        addProductToCartButton.click();
    }

    public void changePizzaDoughAndSize(PizzaItem pizza) {
        PizzaSize pizzaSize = pizza.getPizzaSize();
        pizzaModifyingArea.find(byText(pizza.getDough())).click();
        $(pizzaSize.getSelector()).click();
    }

    public void removeBaseIngredientsFromPizza(PizzaItem pizza) {
        for(AdditiveItem ingredient : pizza.getExcludedItems()) {
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
