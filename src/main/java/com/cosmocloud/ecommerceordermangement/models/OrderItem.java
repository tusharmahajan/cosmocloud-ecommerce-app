package com.cosmocloud.ecommerceordermangement.models;

import lombok.Data;

@Data
public class OrderItem {

    private String productId;
    private int boughtQuantity;
}
