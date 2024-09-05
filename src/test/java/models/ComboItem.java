package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.InputStream;
import java.util.ArrayList;

@Data
public class ComboItem {
    String comboName;
    int comboPrice;
    ArrayList<SimpleItem> products;

    static ClassLoader classLoader = ComboItem.class.getClassLoader();

    public static ComboItem createComboItemFromJsonFile(String filePath) throws Exception {
        try (InputStream inputStream = classLoader
                .getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, ComboItem.class);
        }
    }
}