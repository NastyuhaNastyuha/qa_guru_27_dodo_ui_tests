package testData;

import lombok.Data;

@Data
public class ComboItem {
    String comboName;
    Integer comboPrice;
    String itemId;
    SimpleItem dish_1;
    SimpleItem dish_2;
    SimpleItem drink_1;
    SimpleItem drink_2;
}
