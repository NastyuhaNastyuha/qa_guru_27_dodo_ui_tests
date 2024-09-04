package models;

import data.PizzaSizeEnum;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PizzaItem {
    String pizzaName;
    Integer pizzaPrice;
    PizzaSizeEnum pizzaSizeEnum;
    String dough;
    ArrayList<AdditiveItem>  additiveItems;
    ArrayList<AdditiveItem>  excludedItems;
}