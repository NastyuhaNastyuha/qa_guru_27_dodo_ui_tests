package pages.components;

import com.codeborne.selenide.ElementsCollection;
import models.AdditiveItem;
import models.ComboItem;
import models.SimpleItem;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class CartPopupCombo {
    private final ElementsCollection productCardsInCart = $(".scroll__view").$$("article");

    public void checkSimpleItemsInCombo(ComboItem comboItem) {
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
}
