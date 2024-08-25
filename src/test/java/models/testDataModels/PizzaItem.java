package models.testDataModels;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PizzaItem {
    String pizzaName;
    Integer pizzaPrice;
    PizzaSize pizzaSize;
    String dough;
    ArrayList<AdditiveItem>  additiveItems;
    ArrayList<AdditiveItem>  excludedItems;

}
