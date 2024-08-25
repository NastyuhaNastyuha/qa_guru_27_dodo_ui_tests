package components;

import com.codeborne.selenide.SelenideElement;
import models.testDataModels.AdditiveItem;
import models.testDataModels.SimpleItem;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ComboCardPopup {
    static final SelenideElement addComboToCartButton = $("button.add-button");
    static final SelenideElement productsForReplaceArea = $(".product-selector-scroll__view");
    static final SelenideElement toppingsArea = $(".toppings");
    static final SelenideElement toppingsSaveButton = $("button.save-button");


    public void addComboToCart() {
        addComboToCartButton.click();
    }

    public Integer replaceSimpleItemInCombo(SimpleItem product) {
        $(".slots__view").$("[data-id='" + product.getItemInComboId() +"']").
            find(byText("Заменить")).click();
        productsForReplaceArea.find(byText(product.getItemName())).click();
        return product.getItemSurcharge();
    }

    public Integer changeItemIngredientsInCombo(SimpleItem product) {
        $(".slots__view").$("[data-id='" + product.getItemInComboId() +"']").
                find(byText("Изменить состав")).click();
        Integer surcharge = 0;
        for(AdditiveItem topping : product.getAdditiveItems()) {
            toppingsArea.$("[alt='" + topping.getItemName() + "']").click();
            surcharge += topping.getItemSurcharge();
        }
        toppingsSaveButton.click();
        return surcharge;
    }
}
