package pages.components;

import com.codeborne.selenide.SelenideElement;
import data.DeliveryMethodsEnum;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeliveryMethodPopUp {
    private final SelenideElement pickupButton = $("[data-testid='how_to_get_order_pickup_action']");
    private final SelenideElement deliveryButton = $("[data-testid='how_to_get_order_delivery_action']");
    private final SelenideElement closePopupButton = $(".popup-inner").$("svg");

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