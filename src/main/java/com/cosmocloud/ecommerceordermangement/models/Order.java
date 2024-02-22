package com.cosmocloud.ecommerceordermangement.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String orderId;
    private Date createdOn = new Date();
    private int totalAmount;
    private List<OrderItem> products = new ArrayList<>();
    private Address userAddress;
}
