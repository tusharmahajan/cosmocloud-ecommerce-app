package com.cosmocloud.ecommerceordermangement.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<OrderItem> orderItems;
    private Address address;
}
