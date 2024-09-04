package pages.components;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPopupCommon {
    private final ElementsCollection productCardsInCart = $(".scroll__view").$$("article");

    public CartPopupCommon checkItemName(String itemName) {
        productCardsInCart.findBy(text(itemName)).shouldBe(visible);
        return this;
    }

    public CartPopupCommon checkItemPrice(String itemName, Integer itemPrice) {
        String priceText = productCardsInCart.findBy(text(itemName))
                .$(".current").getText();
        String numericPriceText = priceText.replaceAll("[^0-9]", "");
        int actualPrice = Integer.parseInt(numericPriceText);
        assertThat(actualPrice).isEqualTo(itemPrice);
        return this;
    }

    public CartPopupCommon checkToppingsInProduct(String productName, String toppingName) {
//        productCardsInCart.findBy(text(comboItem.getComboName())).parent().$$(".group")
//                .get(product.getItemOrderInCombo() - 1).shouldHave(text(topping.getItemName()));

        productCardsInCart.findBy(text(productName)).parent().shouldHave(text(toppingName));
        return this;
    }
}
