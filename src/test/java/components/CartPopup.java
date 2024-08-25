package components;

import com.codeborne.selenide.SelenideElement;
import models.testDataModels.AdditiveItem;
import models.testDataModels.ComboItem;
import models.testDataModels.PizzaItem;
import models.testDataModels.SimpleItem;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPopup {

    static final SelenideElement cartArea = $(".scroll__view");


    public void checkThatComboItemInCart_test(ComboItem comboItem) {
        step("Проверить заголовок комбо-товара", () -> {
            cartArea.$$("article").findBy(text(comboItem.getComboName())).shouldHave(text(comboItem.getComboName()));
        });
        step("Проверить стоимость комбо-товара", () -> {
            cartArea.$$("article").findBy(text(comboItem.getComboName())).$(".current").shouldHave(text((comboItem
                    .getComboPrice().toString())));
        });

        for (SimpleItem product : comboItem.getProducts()) {
            step("Проверить заголовок товара " + product.getItemOrderInCombo() + " в комбо", () -> {
                cartArea.$$("article").findBy(text(comboItem.getComboName())).parent().$$(".group")
                        .get(product.getItemOrderInCombo() - 1).shouldHave(text(product.getItemName()));
            });

            for (AdditiveItem topping : product.getAdditiveItems()) {
                step("Проверить, что для товара " + product.getItemOrderInCombo() + " добавлен топпинг", () -> {
                    cartArea.$$("article").findBy(text(comboItem.getComboName())).parent().$$(".group")
                            .get(product.getItemOrderInCombo() - 1).shouldHave(text(topping.getItemName()));
                });

            }

        }
    }

    public void checkThatPizzaItemInCart_test(PizzaItem pizzaItem) {
        step("Проверить заголовок пиццы", () -> {
            cartArea.$$("article").findBy(text(pizzaItem.getPizzaName())).shouldHave(text(pizzaItem.getPizzaName()));

        });

        step("Проверить стоимость пиццы", () -> {
            String priceText = cartArea.$$("article").findBy(text(pizzaItem.getPizzaName()))
                    .$(".current").getText();
            String numericPriceText = priceText.replaceAll("[^0-9]", "");
            int actualPrice = Integer.parseInt(numericPriceText);
            assertThat(actualPrice).isEqualTo(pizzaItem.getPizzaPrice());
        });

        step("Проверить, что добавлен верный размер пиццы", () -> {
            cartArea.$$("article").findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .shouldHave(text(pizzaItem.getPizzaSize().getSize()));
        });

        step("Проверить, что добавлено верное тесто для пиццы", () -> {
            cartArea.$$("article").findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .shouldHave(text(pizzaItem.getDough()));
        });

        for (AdditiveItem additiveItem : pizzaItem.getAdditiveItems()) {
            step("Проверить, что добавлен дополнительный ингредиент в пиццу", () -> {
                cartArea.$$("article").findBy(text(pizzaItem.getPizzaName())).closest("section")
                        .find(withText("+"))
                        .shouldHave(text(additiveItem.getItemName()));
            });
        }

        for (AdditiveItem excludedItem : pizzaItem.getExcludedItems()) {
            step("Проверить, что исключен ингредиент из пиццы", () -> {
                cartArea.$$("article").findBy(text(pizzaItem.getPizzaName())).closest("section")
                        .find(withText("−"))
                        .shouldHave(text(excludedItem.getItemName()));
            });

        }
    }

    public void checkThatItemInCart_test(SimpleItem simpleItem) {
        step("Проверить заголовок товара", () -> {
            cartArea.$$("article").findBy(text(simpleItem.getItemName()))
                    .shouldHave(text(simpleItem.getItemName()));

        });
        step("Проверить стоимость товара", () -> {
            cartArea.$$("article").findBy(text(simpleItem.getItemName())).$(".current")
                    .shouldHave(text((simpleItem.getItemPrice().toString())));
        });
    }
}
