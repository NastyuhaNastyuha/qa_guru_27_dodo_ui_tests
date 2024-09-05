package pages.components;

import com.codeborne.selenide.ElementsCollection;
import models.AdditiveItem;
import models.ComboItem;
import models.PizzaItem;
import models.SimpleItem;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPopup {

    private final ElementsCollection productCardsInCart = $(".scroll__view").$$("article");

    public void checkThatComboItemInCart(ComboItem comboItem) {
        step("Проверить заголовок комбо-товара", () -> {
            productCardsInCart.findBy(text(comboItem.getComboName())).shouldBe(visible);
        });
        step("Проверить стоимость комбо-товара", () -> {
            productCardsInCart.findBy(text(comboItem.getComboName())).$(".current")
                    .shouldHave(text(String.valueOf((comboItem.getComboPrice()))));
        });

        for (SimpleItem product : comboItem.getProducts()) {
            step("Проверить заголовок товара " + product.getItemOrderInCombo() + " в комбо", () -> {
                productCardsInCart.findBy(text(comboItem.getComboName())).parent().$$(".group")
                        .get(product.getItemOrderInCombo() - 1).shouldHave(text(product.getItemName()));
            });

            for (AdditiveItem topping : product.getAdditiveItems()) {
                step("Проверить, что для товара " + product.getItemOrderInCombo() + " добавлен топпинг", () -> {
                    productCardsInCart.findBy(text(comboItem.getComboName())).parent().$$(".group")
                            .get(product.getItemOrderInCombo() - 1).shouldHave(text(topping.getItemName()));
                });

            }

        }
    }

    public void checkThatPizzaItemInCart(PizzaItem pizzaItem) {
        step("Проверить заголовок пиццы", () -> {
            productCardsInCart.findBy(text(pizzaItem.getPizzaName())).shouldHave(text(pizzaItem.getPizzaName()));

        });

        step("Проверить стоимость пиццы", () -> {
            String priceText = productCardsInCart.findBy(text(pizzaItem.getPizzaName()))
                    .$(".current").getText();
            String numericPriceText = priceText.replaceAll("[^0-9]", "");
            int actualPrice = Integer.parseInt(numericPriceText);
            assertThat(actualPrice).isEqualTo(pizzaItem.getPizzaPrice());
        });

        step("Проверить, что добавлен верный размер пиццы", () -> {
            productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .shouldHave(text(pizzaItem.getPizzaSize().getSize()));
        });

        step("Проверить, что добавлено верное тесто для пиццы", () -> {
            productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                    .shouldHave(text(pizzaItem.getDough()));
        });

        for (AdditiveItem additiveItem : pizzaItem.getAdditiveItems()) {
            step("Проверить, что добавлен дополнительный ингредиент в пиццу", () -> {
                productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                        .find(withText("+"))
                        .shouldHave(text(additiveItem.getItemName()));
            });
        }

        for (AdditiveItem excludedItem : pizzaItem.getExcludedItems()) {
            step("Проверить, что исключен ингредиент из пиццы", () -> {
                productCardsInCart.findBy(text(pizzaItem.getPizzaName())).closest("section")
                        .find(withText("−"))
                        .shouldHave(text(excludedItem.getItemName()));
            });

        }
    }

    public void checkThatItemInCart(SimpleItem simpleItem) {
        step("Проверить заголовок товара", () -> {
            productCardsInCart.findBy(text(simpleItem.getItemName()))
                    .shouldHave(text(simpleItem.getItemName()));

        });
        step("Проверить стоимость товара", () -> {
            productCardsInCart.findBy(text(simpleItem.getItemName())).$(".current")
                    .shouldHave(text((simpleItem.getItemPrice().toString())));
        });
    }
}