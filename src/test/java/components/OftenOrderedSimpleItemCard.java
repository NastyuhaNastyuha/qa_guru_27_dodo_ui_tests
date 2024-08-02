package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class OftenOrderedSimpleItemCard {
    static final SelenideElement itemPopUp =
            $("[data-testid='product__card-000d3a240c71be9a11e719be2ab2d427']");
    //static final SelenideElement popUpTitle = $(".title");
    static final SelenideElement popUpTitle =
            $("[data-testid='product__card-000d3a240c71be9a11e719be2ab2d427']")
                    .$(".title");
    //static final SelenideElement toCartButton = $("[type='button']");
    static final SelenideElement toCartButton = $(".popup-inner [data-type='primary']");


    public void addItemToCart(String itemName) {
        popUpTitle.shouldHave(Condition.text(itemName));
        toCartButton.click();
    }
}
