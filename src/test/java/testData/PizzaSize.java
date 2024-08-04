package testData;

import lombok.Getter;

@Getter
public enum PizzaSize {
    BIG("Большая", "[data-testid='menu__pizza_size_Большая']"),
    MEDIUM("Средняя", "[data-testid='menu__pizza_size_Средняя']"),
    SMALL("Маленькая", "[data-testid='menu__pizza_size_Маленькая']");
    private final String name;
    private final String selector;


    PizzaSize(String name, String selector) {
        this.name = name;
        this.selector = selector;
    }
}
