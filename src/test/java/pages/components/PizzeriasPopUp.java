package pages.components;

import com.codeborne.selenide.SelenideElement;
import models.PickupAddress;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class PizzeriasPopUp {
    private final SelenideElement pizzeriasList = $(".pizzerias-list-wrapper");
    private final SelenideElement submitPickupAddressButton = $("[data-testid='menu_product_select']")
            .parent();
    private final SelenideElement pickupAddressItemInList = $(".list-item");
    private final SelenideElement closePopupButton = $(".popup-inner").$("svg");

    public void choosePickupAddress(PickupAddress address) {
        pizzeriasList.$$(".list-item").findBy(text(address.getAddress())).click();
        //Thread.sleep(1000);
        //sleep(1000);
        submitPickupAddressButton.shouldBe(interactable);
        submitPickupAddressButton.click();
    }

    public void checkCityInPickupAddress(String city) {
        pickupAddressItemInList.shouldHave(text(city));
    }

    public void closePopup() {
        closePopupButton.click();
    }
}