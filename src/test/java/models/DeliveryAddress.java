package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.InputStream;

@Data
public class DeliveryAddress {
    String cityForUrl, city, address, entrance, doorCode, floor, apartment, comment;

    static ClassLoader classLoader = DeliveryAddress.class.getClassLoader();

    public static DeliveryAddress createDeliveryAddressFromJsonFile(String filePath) throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, DeliveryAddress.class);
        }
    }
}