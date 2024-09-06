package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class DeliveryMethodPopUp {
    private final SelenideElement pickupButton = $("[data-testid='how_to_get_order_pickup_action']");
    private final SelenideElement deliveryButton = $("[data-testid='how_to_get_order_delivery_action']");
    private final SelenideElement closePopupButton = $(".popup-inner").$("svg");

    public void choosePickup() {
        pickupButton.shouldBe(interactable);
        pickupButton.click();
    }

    public void chooseDelivery() {
        deliveryButton.shouldBe(interactable);
        deliveryButton.click();
    }

    public void closePopup() {
        closePopupButton.click();
    }
}