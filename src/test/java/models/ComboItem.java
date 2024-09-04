package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ComboItem {
    String comboName;
    int comboPrice;
    ArrayList<SimpleItem> products;
}