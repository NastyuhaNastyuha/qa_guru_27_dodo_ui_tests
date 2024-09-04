package models;

import lombok.Data;

@Data
public class DeliveryAddress {
    String city,
     address,
     entrance,
     doorCode,
     floor,
     apartment,
     comment;
}