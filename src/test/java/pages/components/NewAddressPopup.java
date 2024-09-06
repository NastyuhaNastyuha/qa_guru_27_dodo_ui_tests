package pages.components;

import com.codeborne.selenide.SelenideElement;
import models.DeliveryAddress;


import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class NewAddressPopup {
    private final SelenideElement addressInput = $("[data-testid='delivery_placeholder_on_input_street']");
    private final SelenideElement firstAddressSuggestion = $("[data-testid='two-line-list']").$("li");
    private final SelenideElement entranceInput = $("[name='porch']");
    private final SelenideElement doorCodeInput = $("[name='doorCode']");
    private final SelenideElement floorInput = $("[name='floor']");
    private final SelenideElement apartmentInput = $("[name='apartment']");
    private final SelenideElement commentInput = $("[name='comment']");
    private final SelenideElement addAddressButton = $("[data-testid='add_or_save_spinner_button']");

    public void enterNewAddress(DeliveryAddress address) {
        addressInput.click();
        addressInput.setValue(address.getAddress());
        firstAddressSuggestion.shouldBe(interactable);
        firstAddressSuggestion.click();
        entranceInput.shouldBe(interactable);
        entranceInput.click();
        entranceInput.setValue(address.getEntrance());
        doorCodeInput.click();
        doorCodeInput.setValue(address.getDoorCode());
        floorInput.click();
        floorInput.setValue(address.getFloor());
        apartmentInput.click();
        apartmentInput.setValue(address.getApartment());
        commentInput.click();
        commentInput.setValue(address.getComment());
        addAddressButton.click();
    }
}