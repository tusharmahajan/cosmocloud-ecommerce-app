package com.cosmocloud.ecommerceordermangement.service;

import com.cosmocloud.ecommerceordermangement.models.Order;
import com.cosmocloud.ecommerceordermangement.models.OrderItem;
import com.cosmocloud.ecommerceordermangement.models.OrderRequest;
import com.cosmocloud.ecommerceordermangement.models.Product;
import com.cosmocloud.ecommerceordermangement.respository.OrderRepository;
import com.cosmocloud.ecommerceordermangement.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest) {

        List<String> productIds = orderRequest.getOrderItems()
                .stream()
                .map(OrderItem::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(productIds);
        List<OrderItem> orderItems = orderRequest.getOrderItems();
        if(!isOrderPossible(products, orderItems)){
            throw new RuntimeException("Available quantity for few products is not sufficient!!");
        }
        Order order = new Order();
        order.setUserAddress(orderRequest.getAddress());
        int totalAmount = 0;

        for(OrderItem orderItem: orderItems){
            Optional<Product> productDetails = products.stream()
                    .filter(product -> product.getId().equals(orderItem.getProductId())).findAny();

            Product product = productDetails.get();
            int availableQuantity = product.getAvailableQuantity();
            int boughtQuantity = orderItem.getBoughtQuantity();
            product.setAvailableQuantity(availableQuantity-boughtQuantity);

            totalAmount+=(boughtQuantity*product.getPrice());
            productRepository.save(product);
            order.getProducts().add(orderItem);
        }
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    private boolean isOrderPossible(List<Product> products, List<OrderItem> orderItems) {

        for(OrderItem orderItem: orderItems){
            Optional<Product> productDetails = products.stream()
                    .filter(product -> product.getId().equals(orderItem.getProductId())).findAny();
            if(productDetails.isPresent()){
                if(productDetails.get().getAvailableQuantity() < orderItem.getBoughtQuantity()) return false;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
