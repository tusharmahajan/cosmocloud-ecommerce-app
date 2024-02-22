package com.cosmocloud.ecommerceordermangement.service;

import com.cosmocloud.ecommerceordermangement.filters.QueryFilter;
import com.cosmocloud.ecommerceordermangement.models.PageMetadata;
import com.cosmocloud.ecommerceordermangement.models.Product;
import com.cosmocloud.ecommerceordermangement.models.ProductRequest;
import com.cosmocloud.ecommerceordermangement.models.ProductResponse;
import com.cosmocloud.ecommerceordermangement.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addProductDetails(ProductRequest productRequest) {
        Product product = new Product();
        product.setPrice(productRequest.getPrice());
        product.setName(productRequest.getName());
        product.setAvailableQuantity(productRequest.getAvailableQuantity());
        productRepository.save(product);
    }

    public ProductResponse fetchAvailableProducts(List<QueryFilter> queryFilters, int page, int size) {
        Query query = new Query();

        for (QueryFilter queryFilter : queryFilters) {
            Criteria generatedQuery = queryFilter.generateQuery();
            if (generatedQuery != null) {
                query.addCriteria(generatedQuery);
            }
        }
        long total = mongoTemplate.count(query, Product.class);

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Product> products = mongoTemplate.find(query, Product.class);
        Page<Product> productPage = new PageImpl<>(products, pageable, total);

        PageMetadata pageMetadata = PageMetadata.builder()
                .nextOffset(productPage.hasNext() ? page + 1 : -1).
                prevOffset(productPage.hasPrevious() ? page -1 : -1).
                limit(size).total(total).build();

        return ProductResponse.builder().products(products).pageMetadata(pageMetadata).build();
    }

}
