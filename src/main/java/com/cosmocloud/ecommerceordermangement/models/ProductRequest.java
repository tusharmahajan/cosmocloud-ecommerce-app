package com.cosmocloud.ecommerceordermangement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private int price;
    @JsonProperty(value = "quantity")
    private int availableQuantity;
}
