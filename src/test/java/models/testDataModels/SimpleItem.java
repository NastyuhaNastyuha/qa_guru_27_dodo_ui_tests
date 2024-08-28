package models.testDataModels;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SimpleItem {
    String itemName;
    Integer itemPrice;
    Integer itemSurcharge;
    String itemId;
    String itemInComboId;
    Integer itemOrderInCombo;
    ArrayList<AdditiveItem> additiveItems;
}