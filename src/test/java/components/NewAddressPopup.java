package components;

import com.codeborne.selenide.SelenideElement;
import models.testDataModels.DeliveryAddress;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NewAddressPopup {
    static final SelenideElement addressInput = $("[data-testid='delivery_placeholder_on_input_street']");
    static final SelenideElement firstAddressSuggestion = $("[data-testid='two-line-list']").$("li");
    static final SelenideElement entranceInput = $("[name='porch']");
    static final SelenideElement doorCodeInput = $("[name='doorCode']");
    static final SelenideElement floorInput = $("[name='floor']");
    static final SelenideElement apartmentInput = $("[name='apartment']");
    static final SelenideElement commentInput = $("[name='comment']");
    static final SelenideElement addAddressButton = $("[data-testid='add_or_save_spinner_button']");

    public void enterNewAddress(DeliveryAddress address) {
        addressInput.click();
        addressInput.setValue(address.getAddress());
        firstAddressSuggestion.shouldBe(visible, Duration.ofSeconds(10));
        firstAddressSuggestion.click();
        entranceInput.shouldBe(visible, Duration.ofSeconds(10));
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
