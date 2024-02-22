package com.cosmocloud.ecommerceordermangement.respository;

import com.cosmocloud.ecommerceordermangement.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
