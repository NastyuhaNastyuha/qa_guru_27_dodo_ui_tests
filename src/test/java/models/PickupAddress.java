package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.InputStream;

@Data
public class PickupAddress {
    String city, address, cityForUrl;

    static ClassLoader classLoader = PickupAddress.class.getClassLoader();

    public static PickupAddress createPickupAddressFromJsonFile(String filePath) throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, PickupAddress.class);
        }
    }
}