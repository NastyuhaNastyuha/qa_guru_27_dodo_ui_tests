package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DeliveryMethodPopUp {
    static final SelenideElement pickupButton = $("[data-testid='how_to_get_order_pickup_action']");
    static final SelenideElement deliveryButton = $("[data-testid='how_to_get_order_delivery_action']");

    public void choosePickup() {
        pickupButton.click();
    }

    public void chooseDelivery() {
        deliveryButton.click();
    }
}
