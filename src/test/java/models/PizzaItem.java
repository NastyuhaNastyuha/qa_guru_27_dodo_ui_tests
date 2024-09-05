package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.PizzaSizeEnum;
import lombok.Data;

import java.io.InputStream;
import java.util.ArrayList;

@Data
public class PizzaItem {
    String pizzaName;
    Integer pizzaPrice;
    PizzaSizeEnum pizzaSize;
    String dough;
    ArrayList<AdditiveItem>  additiveItems;
    ArrayList<AdditiveItem>  excludedItems;

    static ClassLoader classLoader = PizzaItem.class.getClassLoader();

    public static PizzaItem createPizzaItemFromJsonFile(String filePath) throws Exception {
        try (InputStream inputStream = classLoader
                .getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, PizzaItem.class);
        }
    }
}