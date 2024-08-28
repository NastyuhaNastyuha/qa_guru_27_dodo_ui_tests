package models.testDataModels;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ComboItem {
    String comboName;
    Integer comboPrice;
    ArrayList<SimpleItem> products;
}