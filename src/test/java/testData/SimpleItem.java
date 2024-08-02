package testData;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SimpleItem {
    String itemName;
    Integer itemPrice;
    Integer itemSurcharge;
    String itemId;
    String itemWeight;
    ArrayList<AdditiveItem> additiveItems;
}
