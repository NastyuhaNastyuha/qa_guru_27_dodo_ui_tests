package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectCityPopUp {
    private final SelenideElement searchInput =
            $("[data-testid='locality-selector-popup__search-input']");
    private final ElementsCollection cityButton = $$(".locality-selector-popup__table__group");


    public void selectCityBySearch(String city) {
        //searchInput.shouldBe(visible, Duration.ofSeconds(30));
        searchInput.shouldBe(interactable);
        searchInput.click();
        searchInput.setValue(city);
        cityButton.findBy(text(city)).click();
    }
}