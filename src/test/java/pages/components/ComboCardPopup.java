package pages.components;

import com.codeborne.selenide.SelenideElement;
import models.AdditiveItem;
import models.SimpleItem;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ComboCardPopup {
    private final SelenideElement addComboToCartButton = $("button.add-button");
    private final SelenideElement productsForReplaceArea = $(".product-selector-scroll__view");
    private final SelenideElement toppingsArea = $(".toppings");
    private final SelenideElement toppingsSaveButton = $("button.save-button");


    public void addComboToCart() {
        addComboToCartButton.click();
    }

    public Integer replaceSimpleItemInCombo(SimpleItem product) {
        $(".slots__view").$("[data-id='" + product.getItemInComboId() + "']").
                find(byText("Заменить")).click();
        productsForReplaceArea.find(byText(product.getItemName())).click();
        return product.getItemSurcharge();
    }

    public Integer changeItemIngredientsInCombo(SimpleItem product) {
        $(".slots__view").$("[data-id='" + product.getItemInComboId() + "']").
                find(byText("Изменить состав")).click();
        Integer surcharge = 0;
        for (AdditiveItem topping : product.getAdditiveItems()) {
            toppingsArea.$("[alt='" + topping.getItemName() + "']").click();
            surcharge += topping.getItemSurcharge();
        }
        toppingsSaveButton.click();
        return surcharge;
    }
}