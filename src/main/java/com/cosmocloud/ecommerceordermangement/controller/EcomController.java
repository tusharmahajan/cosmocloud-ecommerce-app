package com.cosmocloud.ecommerceordermangement.controller;

import com.cosmocloud.ecommerceordermangement.factory.FilterFactory;
import com.cosmocloud.ecommerceordermangement.filters.QueryFilter;
import com.cosmocloud.ecommerceordermangement.models.Order;
import com.cosmocloud.ecommerceordermangement.models.OrderRequest;
import com.cosmocloud.ecommerceordermangement.models.ProductRequest;
import com.cosmocloud.ecommerceordermangement.models.ProductResponse;
import com.cosmocloud.ecommerceordermangement.service.OrderService;
import com.cosmocloud.ecommerceordermangement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal")
public class EcomController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FilterFactory filterFactory;

    @Autowired
    private OrderService orderService;

    @PostMapping("/addProducts")
    public String addProduct(@RequestBody ProductRequest productRequest){
        productService.addProductDetails(productRequest);
        return "Created successfully";
    }

    @GetMapping("/fetchAvailableProducts")
    public ProductResponse fetchAvailableProducts(@RequestParam(required = false) Integer minPrice,
                                                  @RequestParam(required = false) Integer maxPrice,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size){

        List<QueryFilter> queryFilters = filterFactory.getFilters(minPrice, maxPrice);
        return productService.fetchAvailableProducts(queryFilters, page, size);
    }

    @PostMapping("/createOrder")
    public Order addProduct(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

}
