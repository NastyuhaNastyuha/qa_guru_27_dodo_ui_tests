package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.InputStream;
import java.util.ArrayList;

@Data
public class SimpleItem {
    static ClassLoader classLoader = SimpleItem.class.getClassLoader();

    String itemName;
    Integer itemPrice;
    Integer itemSurcharge;
    String itemId;
    String itemInComboId;
    Integer itemOrderInCombo;
    ArrayList<AdditiveItem> additiveItems;

    public static SimpleItem createSimpleItemFromJsonFile(String filePath) throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, SimpleItem.class);
        }
    }
}