package components;

import com.codeborne.selenide.SelenideElement;
import models.testDataModels.PickupAddress;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PizzeriasPopUp {
    static final SelenideElement pizzeriasList = $(".pizzerias-list-wrapper");
    static final SelenideElement submitPickupAddressButton = $("[data-testid='menu_product_select']")
            .parent();
    static final SelenideElement pickupAddressItemInList = $(".list-item");
    static final SelenideElement closePopupButton = $(".popup-inner").$("svg");

    public void choosePickupAddress(PickupAddress address) throws InterruptedException {
        pizzeriasList.$$(".list-item").findBy(text(address.getAddress())).click();
        Thread.sleep(1000);
        submitPickupAddressButton.click();
    }

    public void checkCityInPickupAddress(String city) {
        pickupAddressItemInList.shouldHave(text(city));
    }

    public void closePopup() {
        closePopupButton.click();
    }
}
