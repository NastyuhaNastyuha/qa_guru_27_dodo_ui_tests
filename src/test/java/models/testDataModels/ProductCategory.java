package models.testDataModels;

import lombok.Getter;

@Getter
public enum ProductCategory {
    PIZZA("Пиццы", "#guzhy"),
    COMBO("Комбо", "#nfjka");
    private final String name;
    private final String selector;

    ProductCategory(String name, String selector) {
        this.name = name;
        this.selector = selector;
    }
}
