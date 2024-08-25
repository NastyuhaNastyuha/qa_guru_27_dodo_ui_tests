package models.testDataModels;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class SimpleItemWithBuilder {
    String itemName;
    Integer itemPrice;
    Integer itemSurcharge;
    String itemId;
    String itemInComboId;
    Integer itemOrderInCombo;
    String itemWeight;
    ArrayList<AdditiveItem> additiveItems;
}
