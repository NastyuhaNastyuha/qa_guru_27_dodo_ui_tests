package data;

import lombok.Getter;

@Getter
public enum ProductCategoryEnum {
    PIZZA("Пиццы", "#guzhy"),
    COMBO("Комбо", "#nfjka");
    private final String name;
    private final String selector;

    ProductCategoryEnum(String name, String selector) {
        this.name = name;
        this.selector = selector;
    }
}
