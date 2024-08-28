package components;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeliveryMethodPopUp {
    static final SelenideElement pickupButton = $("[data-testid='how_to_get_order_pickup_action']");
    static final SelenideElement deliveryButton = $("[data-testid='how_to_get_order_delivery_action']");
    static final SelenideElement closePopupButton = $(".popup-inner").$("svg");

    public void choosePickup() {
        pickupButton.shouldBe(visible, Duration.ofSeconds(30));
        pickupButton.click();
    }

    public void chooseDelivery() {
        deliveryButton.shouldBe(visible, Duration.ofSeconds(30));
        deliveryButton.click();
    }

    public void closePopup() {
        closePopupButton.click();
    }
}
