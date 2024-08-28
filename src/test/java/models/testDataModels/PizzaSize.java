package models.testDataModels;

import lombok.Getter;

@Getter
public enum PizzaSize {
    BIG("Большая", "[data-testid='menu__pizza_size_Большая']", "35 см"),
    MEDIUM("Средняя", "[data-testid='menu__pizza_size_Средняя']", "30 см"),
    SMALL("Маленькая", "[data-testid='menu__pizza_size_Маленькая']", "25 см");
    private final String name;
    private final String selector;
    private final String size;

    PizzaSize(String name, String selector, String size) {
        this.name = name;
        this.selector = selector;
        this.size = size;
    }
}
