package com.cosmocloud.ecommerceordermangement.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private int price;
    private int availableQuantity;
}
