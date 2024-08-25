package models.testDataModels;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ComboItem {
    String comboName;
    Integer comboPrice;
    //String itemId;
    ArrayList<SimpleItem> products;

    SimpleItem dish_1;
    SimpleItem dish_2;
    SimpleItem drink_1;
    SimpleItem drink_2;
}
