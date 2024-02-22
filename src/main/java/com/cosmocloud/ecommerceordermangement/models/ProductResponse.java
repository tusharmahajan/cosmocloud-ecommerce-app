package com.cosmocloud.ecommerceordermangement.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductResponse {

    private List<Product> products;
    private PageMetadata pageMetadata;

}
